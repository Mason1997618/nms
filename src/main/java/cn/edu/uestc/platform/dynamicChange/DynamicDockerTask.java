package cn.edu.uestc.platform.dynamicChange;

import java.awt.TextArea;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.openstack4j.api.OSClient.OSClientV3;

import cn.edu.uestc.platform.factory.OSClientFactory;
import cn.edu.uestc.platform.utils.Constants;
import cn.edu.uestc.platform.winter.docker.PortUtils;

public class DynamicDockerTask implements Runnable{
	private TextArea text;
	public DynamicDockerTask(TextArea text) {
		this.text =  text;
	}

	private int index = 1;// 为当前的分钟

	@Override
	public void run() {
		System.out.println("************************************************Time " + index + " min************************************************");
		text.setText(text.getText()+"\n"+ "***********************Time " + index + " min*****************************");
		index++;// 要保证时间的正确性
		TreeSet<NewLink> links = Filters.readMinuteMEORL(index - 1 + "");// 拿到当前分钟的链路
		TreeSet<NewLink> linksBefore = Filters.readMinuteMEORL((index - 2) + "");// 拿到上一分钟的链路
		System.out.println("delLeoToLeo----" + changeLinks(linksBefore, links));
		System.out.println("addLeoToLeo----" + changeLinks(links,linksBefore));
		text.setText(text.getText()+"\ndelLeoToLeo----" + changeLinks(linksBefore, links));
		text.setText(text.getText()+"\naddLeoToLeo----" + changeLinks(links,linksBefore));
//		//GEO TO LEO
		TreeSet<NewLink> lgLinks = Filters.readMinuteMEOToGEO(index - 1 + "");
		TreeSet<NewLink> lgLinksBefore = Filters.readMinuteMEOToGEO(index - 2 + "");
		System.out.println("delGEOToLeo----" + changeLinks( lgLinksBefore,lgLinks));
		System.out.println("addGEOToLeo----" + changeLinks(lgLinks, lgLinksBefore));
		text.setText(text.getText()+"\ndelGEOToLeo----" + changeLinks( lgLinksBefore,lgLinks));
		text.setText(text.getText()+"\naddGEOToLeo----" + changeLinks(lgLinks, lgLinksBefore));
		
		OSClientV3 os = OSClientFactory.authenticate("zph", "123456", Constants.ZPH_PROJECT_ID);
		// LeoToLeo删除
		for (NewLink link : changeLinks(linksBefore, links)) {
			PortUtils.delPort(os, IPCreateFactory.searchData(link, IPCreateFactory.createTableLR())[0], IPCreateFactory.searchData(link, IPCreateFactory.createTableLR())[2]);
			PortUtils.delPort(os, IPCreateFactory.searchData(link, IPCreateFactory.createTableLR())[1], IPCreateFactory.searchData(link, IPCreateFactory.createTableLR())[3]);
		}
		
		// LeoToLeo增加
		for (NewLink link : changeLinks(links,linksBefore)) {
			PortUtils.addPort(os, IPCreateFactory.searchData(link, IPCreateFactory.createTableLR())[0], IPCreateFactory.searchData(link, IPCreateFactory.createTableLR())[2]);
			PortUtils.addPort(os, IPCreateFactory.searchData(link, IPCreateFactory.createTableLR())[1], IPCreateFactory.searchData(link, IPCreateFactory.createTableLR())[3]);
		}

		// GEOToLeo删除,只需要删除LEO的数据
		for (NewLink link : changeLinks( lgLinksBefore,lgLinks)) {
			//DynamicFactory.delPort(os, IPCreateFactory.searchData(link, IPCreateFactory.createTableGEOToMEO())[0], IPCreateFactory.searchData(link, IPCreateFactory.createTableGEOToMEO())[2]);
			PortUtils.delPort(os, IPCreateFactory.searchData(link, IPCreateFactory.createTableGEOToMEO())[1], IPCreateFactory.searchData(link, IPCreateFactory.createTableGEOToMEO())[3]);
		}
		
		// GEOToLeo增加，只需要增加LEO的数据
		for (NewLink link : changeLinks(lgLinks, lgLinksBefore)) {
			//DynamicFactory.addPort(os, IPCreateFactory.searchData(link, IPCreateFactory.createTableGEOToMEO())[0], IPCreateFactory.searchData(link, IPCreateFactory.createTableGEOToMEO())[2]);
			PortUtils.addPort(os, IPCreateFactory.searchData(link, IPCreateFactory.createTableGEOToMEO())[1], IPCreateFactory.searchData(link, IPCreateFactory.createTableGEOToMEO())[3]);
		}
	}

	// 要删除的链路/增加的链路
	public Set<NewLink> changeLinks(Set<NewLink> set1, Set<NewLink> set2) {
		Set<NewLink> set1Cp = new HashSet<>(set1);
		Set<NewLink> set2Cp = new HashSet<>(set2);
		Iterator<NewLink> linksIter = set1Cp.iterator();
		while (linksIter.hasNext()) {
			NewLink link = linksIter.next();
			for (NewLink linkBefore : set2Cp) {
				// 如果link和linkBefore相同，则将link删除
				if ((link.getFrom().equals(linkBefore.getFrom()) && link.getEnd().equals(linkBefore.getEnd()))
						|| (link.getFrom().equals(linkBefore.getEnd()) && link.getEnd().equals(linkBefore.getFrom()))) {
					linksIter.remove();
				}
			}
		}
		return set1Cp;
	}
	
	public static void main(String[] args) {
		new DynamicTask(null).run();
	}
}
