package org.romanchi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * romanchi40160@gmail.com
 * Roman Malyarchuk
 *  31.08.2017
 */
public class App {
    public static void printSilpoGoods() throws IOException {
        Document pageDocument = Jsoup.connect("http://silpo.ua/ua/actions/priceoftheweek/?PAGEN_1=1&").get();
        int pages = pageDocument.select(".page div").size();
        for(int page = 1; page <= pages; page++){
            Document doc = Jsoup.connect("http://silpo.ua/ua/actions/priceoftheweek/?PAGEN_1=" + page + "&").get();
            Elements goods = doc.select(".photo");
            goods.forEach(good -> {
                String goodName = good.select(".img h3").text();
                String goodNewPrice = good.select(".price_2014_new .hrn").text() +"."+ good.select(".price_2014_new .kop").text();
                String goodOldPrice = good.select(".price_2014_old .hrn").text() + "." + good.select(".price_2014_old .kop").text();
                System.out.println(String.format("%s Акційна ціна %s грн. Нормальна ціна %s грн.", goodName, goodNewPrice, goodOldPrice));
            });
        }
    }
    public static void printAtbGoods() throws IOException {
        Document doc = Jsoup.connect("http://www.atbmarket.com/hot/akcii/economy").get();
        Elements goods = doc.select(".promo_list li");
        goods.forEach(good -> {
            String goodName = good.select(".promo_info_text").text();
            String kop = good.select(".promo_price span").get(0).text();
            good.select(".promo_price span").forEach(span -> span.html(""));
            String goodNewPrice = good.select(".promo_price").text() + "." + kop;
            String goodOldPrice = good.select(".promo_old_price").text();
            System.out.println(String.format("%s Акційна ціна %s грн. Нормальна ціна %s грн.", goodName, goodNewPrice, goodOldPrice));
        });
    }
    public static void main( String[] args ) throws IOException {
        App.printAtbGoods();
        App.printSilpoGoods();
    }
}
