import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.IDN;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


class UrlObject {
    private String name;
    private String url;

    public UrlObject(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}

public class parsingUrl {


    public static List<UrlObject> convertToObjectList(String jsonStr) throws JSONException {
        List<UrlObject> objectList = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(jsonStr);
        JSONArray urlsArray = jsonObject.getJSONArray("urls");

        for (int i = 0; i < urlsArray.length(); i++) {
            JSONObject urlObject = urlsArray.getJSONObject(i);
            String name = urlObject.getString("name");
            String url = urlObject.getString("url");

            objectList.add(new UrlObject(name, url));
        }

        return objectList;
    }

    public static void main(String[] args) throws JSONException, IOException {
//        main();
        getjson();
//        accessUrl("http://肥猫.com",5000);
//        getPunycode("http://肥猫.com");

        

    }


    public static void main() throws JSONException {
        String json = "{\n" +
                "  \"urls\": [\n" +
                "    {\n" +
                "      \"url\": \"http://肥猫.com\",\n" +
                "      \"name\": \"新肥猫\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"ygbox\",\n" +
                "      \"url\": \"https://gitee.com/Hanby0511/tv_json/raw/master/ygbox.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"ygbox1\",\n" +
                "      \"url\": \"https://gitee.com/Hanby0511/tv_json/raw/master/ygbox1.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"vip2\",\n" +
                "      \"url\": \"https://gitee.com/Hanby0511/tv_json/raw/master/vip2.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"hanby\",\n" +
                "      \"url\": \"https://gitee.com/Hanby0511/tv_json/raw/master/hanby.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"child\",\n" +
                "      \"url\": \"https://gitee.com/Hanby0511/tv_json/raw/master/child.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"放牛\",\n" +
                "      \"url\": \"http://tvbox.王二小放牛娃.xyz\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"云星日记\",\n" +
                "      \"url\": \"http://itvbox.cc/云星日记\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"OK猫\",\n" +
                "      \"url\": \"https://jihulab.com/okcaptain/kko/-/raw/main/tv.txt\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"高山流云\",\n" +
                "      \"url\": \"https://www.gitlink.org.cn/api/wxrj/wx/raw/wx.json?ref=master\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"俊于\",\n" +
                "      \"url\": \"http://home.jundie.top:81/top98.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"巧技\",\n" +
                "      \"url\": \"http://pandown.pro/tvbox/tvbox.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Ray2\",\n" +
                "      \"url\": \"https://dxawi.github.io/0/0.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"巧儿\",\n" +
                "      \"url\": \"http://pandown.pro/tvbox/tvbox.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"俊哥\",\n" +
                "      \"url\": \"http://home.jundie.top:81/top98.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"天天开心\",\n" +
                "      \"url\": \"http://tv.rihou.cc/天天开心\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"香雅晴\",\n" +
                "      \"url\": \"https://ghproxy.net/https://raw.githubusercontent.com/xyq254245/xyqonlinerule/main/XYQTVBox.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"运输车\",\n" +
                "      \"url\": \"https://weixine.net/ysc.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"货运车\",\n" +
                "      \"url\": \"https://cf.weixine.net/ysc.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://我不是.摸鱼儿.top\",\n" +
                "      \"name\": \"摸鱼\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://xhww.fun/小米/DEMO.json\",\n" +
                "      \"name\": \"小米\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"动漫城\",\n" +
                "      \"url\": \"https://www.yingm.cc/dm/dm.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"业余阿里\",\n" +
                "      \"url\": \"http://yydf.540734621.xyz/yydf/yydf/bd/ali.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"影探\",\n" +
                "      \"url\": \"http://www.lyyytv.cn/yt/yt.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"飘零\",\n" +
                "      \"url\": \"https://100km.top\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"小马\",\n" +
                "      \"url\": \"https://szyyds.cn/tv/x.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"魔饭\",\n" +
                "      \"url\": \"https://jihulab.com/yw88075/tvbox/-/raw/main/dr/js.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"拾光4K\",\n" +
                "      \"url\": \"https://gh.con.sh/https://raw.githubusercontent.com/xmbjm/vip4K/main/4Kvip.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"拾光畅谈\",\n" +
                "      \"url\": \"https://gh.con.sh/https://raw.githubusercontent.com/xmbjm/vip/main/vip4k.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"一夫莫当\",\n" +
                "      \"url\": \"http://yydf.540734621.xyz/yydf/yydf/bd/watson/watson.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"业余PG\",\n" +
                "      \"url\": \"http://yydf.540734621.xyz/yydf/yydf/bd/pg.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://gh-proxy.com/https://raw.githubusercontent.com/gaotianliuyun/gao/master/XYQ.json\",\n" +
                "      \"name\": \"香雅晴\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://tvbox.cainisi.cf\",\n" +
                "      \"name\": \"菜妮丝\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://home.jundie.top:81/top98.json\",\n" +
                "      \"name\": \"俊佬\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://xhdwc.tk/0\",\n" +
                "      \"name\": \"Ray明\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://xhdwc.tk/0\",\n" +
                "      \"name\": \"dxzwi0\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://raw.liucn.cc/box/m.json\",\n" +
                "      \"name\": \"老刘备\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://cn.kstore.space/download/2863/01.txt\",\n" +
                "      \"name\": \"潇洒\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://cn.kstore.space/download/2883/0110.txt\",\n" +
                "      \"name\": \"蚂蚁\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://tv.nxog.top/m/111.php?ou=%E6%AC%A7%E6%AD%8C&mz=index2&xl=&jar=index2\",\n" +
                "      \"name\": \"欧歌\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://52pan.top:81/api/v3/file/get/174964/%E5%90%BE%E7%88%B1%E8%AF%84%E6%B5%8B.m3u?sign=rPssLoffquDXszCARt6UNF8MobSa1FA27XomzOluJBY%3D%3A0\",\n" +
                "      \"name\": \"吾爱\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://itvbox.cc/tvbox/云星日记/1.m3u8\",\n" +
                "      \"name\": \"云星日记\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://meowtv.cn/tv\",\n" +
                "      \"name\": \"喵影视\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://notabug.org/qizhen15800/My9394/raw/master/ProfessionalEdition.m3u8\",\n" +
                "      \"name\": \"不良帅\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://www.macms.pro/box/3.json\",\n" +
                "      \"name\": \"万达\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://szyyds.cn/tv/x.json\",\n" +
                "      \"name\": \"小马\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://gh-proxy.com/https://raw.githubusercontent.com/yydfys/yydf/main/yydf/yydfjk.json\",\n" +
                "      \"name\": \"业余打发\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://rihou.cc:88/荷城茶秀\",\n" +
                "      \"name\": \"荷城茶秀\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://tt.iitvba.com/vip/tv.json\",\n" +
                "      \"name\": \"iitvba\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://tv.奥利给.top\",\n" +
                "      \"name\": \"茄子库线路\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://xhztv.top/xhz\",\n" +
                "      \"name\": \"小盒子\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://xhztv.top/4k.json\",\n" +
                "      \"name\": \"4k接口\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://清清.摸鱼儿.top\",\n" +
                "      \"name\": \"失眠的夜\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://meowtv.top/tv\",\n" +
                "      \"name\": \"喵影视\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://100km.top/0\",\n" +
                "      \"name\": \"骚零\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://gh-proxy.com/https://raw.githubusercontent.com/gaotianliuyun/gao/master/js.json\",\n" +
                "      \"name\": \"高天流云\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://sinopacifichk.com/box/56.txt\",\n" +
                "      \"name\": \"太平洋\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://jihulab.com/yueer/yueera/-/raw/main/11.17/yueer.json\",\n" +
                "      \"name\": \"月儿\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://wds.ecsxs.com/230989.json\",\n" +
                "      \"name\": \"春盈\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://cdn.qiaoji8.com/tvbox.json\",\n" +
                "      \"name\": \"巧计\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://www.wya6.cn/tv/yc.json\",\n" +
                "      \"name\": \"无意\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83E\uDD20潇洒仓\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/SCXSVIP/TV/main/01.txt\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF5A饭太硬\",\n" +
                "      \"url\": \"http://饭太硬.com/tv\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC3C肥猫源\",\n" +
                "      \"url\": \"http://肥猫.com\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDE9A运输车\",\n" +
                "      \"url\": \"https://weixine.net/ysc.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83E\uDD35高天仓\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/gaotianliuyun/gao/master/js.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF2A南风\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/yoursmile66/TVBox/main/XC.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC1F摸鱼儿\",\n" +
                "      \"url\": \"http://我不是.摸鱼儿.top\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC4COK仓\",\n" +
                "      \"url\": \"https://gitee.com/okjack/okk/raw/master/ok.txt\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDCFD影视仓\",\n" +
                "      \"url\": \"https://download.kstore.space/download/2883/nzk/nzk0722.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF19月光源\",\n" +
                "      \"url\": \"https://gitlab.com/guot55/bh/-/raw/main/pro.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDDC3\uFE0E宝盒源\",\n" +
                "      \"url\": \"https://gitlab.com/guot55/bh/-/raw/main/box%E5%8E%9F.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF12月光宝盒\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/guot55/YGBH/main/pro.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83E\uDD35老刘备\",\n" +
                "      \"url\": \"https://raw.liucn.cc/box/m.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC1D契斯特\",\n" +
                "      \"url\": \"https://qist.tycng.com/jsm.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDFA4欧歌主线\",\n" +
                "      \"url\": \"http://tv.nxog.top/m/111.php?ou=欧歌&mz=index2&xl=&jar=index2\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC96念心线路\",\n" +
                "      \"url\": \"https://gitee.com/nianxinxz/miao/raw/master/tvbox.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDD2F忙忙软件\",\n" +
                "      \"url\": \"https://www.gitlink.org.cn/api/Mrjkmm/MMRJK/raw/MMTV.json?ref=master\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"➰\uFE0F环宇轩\",\n" +
                "      \"url\": \"https://gitee.com/hyxuan_admin/xnf/raw/master/xnf.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"❦缝纫机\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/kunkka1986/my.img/main/frjbox.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDD25amin\",\n" +
                "      \"url\": \"https://d.kstore.space/download/7587/amin.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF29\uFE0F雷蒙影视\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/n3rddd/N3RD/master/JN/lem.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF2E小武哥\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/wwb521/live/main/movies.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF1F时光源\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/xmbjm/xmbjm/main/api.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDCDA拾光畅谈\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/xmbjm/xmbjmjk/main/xmbjm.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"☃\uFE0FPG源\",\n" +
                "      \"url\": \"https://g.3344550.xyz/raw.githubusercontent.com/aaaafeng123/PG/main/jsm.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC27PizazzGY\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/PizazzGY/TVBox/main/api.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83E\uDD89恩惠源\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/shichuanenhui/TvBox/main/fty.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83E\uDD4B其人之道\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/qirenzhidao/tvbox18/main/app.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83E\uDD13GH-alantang1977\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/alantang1977/X/main/X.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDFBDGH-小牛动漫\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/ls125781003/tvboxtg/main/%E5%8A%A8%E6%BC%AB%E9%A2%91%E9%81%93/api.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83E\uDDD2\uD83C\uDFFBGH-小牛少儿\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/ls125781003/tvboxtg/main/%E5%B0%91%E5%84%BF%E9%A2%91%E9%81%93/api.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF4BGH-qist(OK影视)\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/qist/tvbox/master/0821.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF44GH-cyao2q\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/cyao2q/files/master/n.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83E\uDDFBGH-anaer\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/anaer/Meow/main/meow.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDCA6GH-aaliluya1977\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/aliluya1977/TVBox/master/shg.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC96GH-andyMuj\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/CandyMuj/ResourceInterface/main/TVBox/candymuj.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF4AGH-hackyjso(橘子柚)\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/hackyjso/TVbox/main/jzy.txt\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC3BGH-xiongjian\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/xiongjian83/TvBox/main/X.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDD0CGH-bestpvp(插兜)\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/bestpvp/tm/main/source/stable/main.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC54GH-wwb521\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/wwb521/live/main/movies.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDE3BT00700\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/T00700/TVBoxSE/master/FongMi.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83E\uDD65莫名的悲伤\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/Dong-learn9/TVBox-zyjk/main/tvbox2.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83E\uDD85网游鹰\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/aa123jg/tvbox/main/wyyk/wyyk.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC09小白龙\",\n" +
                "      \"url\": \"http://39.101.135.137:8080\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF0Baliluya1977\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/aliluya1977/TVBox/master/shg.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF34多多影音\",\n" +
                "      \"url\": \"https://gitee.com/shangyunvip/shangyunvipdc/raw/master/duoduo/dduo.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDFA1业余打发\",\n" +
                "      \"url\": \"http://yydf.540734621.xyz/QQ/yydf.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDDF9天微线路\",\n" +
                "      \"url\": \"https://gitee.com/tvkj/tw/raw/main/svip.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF38西夏影视\",\n" +
                "      \"url\": \"https://d.kstore.space/download/2912/0318.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF39爱尚影视\",\n" +
                "      \"url\": \"https://gitee.com/hailin886/fty/raw/master/tvbox/aishang.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF44挺好线路\",\n" +
                "      \"url\": \"http://ztha.top/TVBox/thdjk.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDCDB整挺好啊\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/ZTHA000/tvbox/main/thzx1.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"⭐恒星资料网\",\n" +
                "      \"url\": \"https://gitee.com/heng546199810/hxvip8/raw/master/hxvip3.txt\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC3A猎狼\",\n" +
                "      \"url\": \"https://yyds.lltv8.top/yyds.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC1F摸鱼\",\n" +
                "      \"url\": \"https://jihulab.com/moyuer/momoyu/-/raw/main/moyu.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83E\uDD89夜猫子\",\n" +
                "      \"url\": \"https://jihulab.com/ymz1231/xymz/-/raw/main/ymz\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF3A春盈天下\",\n" +
                "      \"url\": \"https://wds.ecsxs.com/230989.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF12月儿\",\n" +
                "      \"url\": \"https://jihulab.com/yueer/yueera/-/raw/main/11.17/yueer.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC60万达\",\n" +
                "      \"url\": \"https://www.macms.pro/box/3.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDFAC影探\",\n" +
                "      \"url\": \"http://www.lyyytv.cn/yt/yt.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83E\uDD96龙门影视1\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/lmclub/box/main/app/conf/tv.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC9E乐享汇J\",\n" +
                "      \"url\": \"https://jihulab.com/lxhfans/TV/-/raw/master/tv.txt\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF1F乐享汇G\",\n" +
                "      \"url\": \"https://g.3344550.xyz/raw.githubusercontent.com/lxhfans/TV/master/tv.txt\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF9E短剧剧集\",\n" +
                "      \"url\": \"https://g.3344550.xyz/raw.githubusercontent.com/lxhfans/TV/master/duanju.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF6F蜂蜜1\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/FongMi/CatVodSpider/main/json/config.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC95全部4K\",\n" +
                "      \"url\": \"https://codeberg.org/hengxing/hx/raw/branch/main/qb4k.txt\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDFDA万达影视\",\n" +
                "      \"url\": \"http://sinopacifichk.com/box/56.txt\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC7F心魔线路\",\n" +
                "      \"url\": \"https://jihulab.com/yw88075/tvbox/-/raw/main/dr/js.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC3C熊猫不是猫\",\n" +
                "      \"url\": \"https://jihulab.com/yw88075/tvbox/-/raw/main/dr/js.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"✨\uFE0F壹梦在线\",\n" +
                "      \"url\": \"http://qrh.yimkj.cn/ym/%E5%A3%B9%E6%A2%A6.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF40俊宇\",\n" +
                "      \"url\": \"http://home.jundie.top:81/top98.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDE1D非凡\",\n" +
                "      \"url\": \"https://codeberg.org/jgfx/master/raw/branch/master/ff1130.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83C\uDF43霜辉月明\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/lm317379829/PyramidStore/pyramid/py.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83E\uDDFBzhixc\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/zhixc/CatVodTVSpider/main/json/tvbox_config.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDCFAlystv\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/lystv/short/main/%E5%BD%B1%E8%A7%86/cs.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC36二哈\",\n" +
                "      \"url\": \"https://g.3344550.xyz/https://raw.githubusercontent.com/2hacc/TVBox/main/tvbox.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDEE1大卫º\",\n" +
                "      \"url\": \"https://dxawi.github.io/0/0.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC31喵呜\",\n" +
                "      \"url\": \"https://anaer.github.io/Meow/meow.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDCA6巧技2\",\n" +
                "      \"url\": \"http://pandown.pro/tvbox/tvbox.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDD18心魔\",\n" +
                "      \"url\": \"https://jihulab.com/yw88075/tvbox/-/raw/main/ywxl.json\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"\uD83D\uDC9D恋影\",\n" +
                "      \"url\": \"https://www.lianyingtv.com/fast/fast\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        List<UrlObject> urlObjects = convertToObjectList(json);

        //输出不能访问的url
//        List<UrlObject> unreachableUrls = new ArrayList<>();
//        long timeoutMillis = 10000;
//        for (int i = 0; i < urlObjects.size(); i++) {
//            UrlObject obj = urlObjects.get(i);
//            if (!accessUrl(obj.getUrl(), timeoutMillis)) {
//                unreachableUrls.add(obj);
//                urlObjects.remove(obj);
//                i--;
//            }
//        }
//        for (UrlObject obj : unreachableUrls) {
//            System.out.println(obj.getName() + " - " + obj.getUrl());
//        }

//        for (UrlObject obj : urlObjects) {
//            System.out.println(obj.getName() + " - " + obj.getUrl());
//        }

        //把能用的url重新转换为json
        long timeoutMillis = 5000;
        String resultJson = filterAndConvertToJson(urlObjects, timeoutMillis);

        System.out.println(resultJson);

//        System.out.println(urlObjects);
    }

    public static String filterAndConvertToJson(List<UrlObject> urlObjects, long timeoutMillis) {
        List<UrlObject> reachableUrls = new ArrayList<>();
        for (UrlObject obj : urlObjects) {
            if (accessUrl(obj.getUrl(), timeoutMillis)) {
                reachableUrls.add(obj);
            }
        }

        System.out.println("去重前：" + reachableUrls.size());
        List<Map<String, Object>> result = reachableUrls.stream()
                .map(obj -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("url", obj.getUrl());
                    map.put("name", obj.getName());
                    // 将其他属性添加到Map中
                    return map;
                })
                .collect(Collectors.toList());
        System.out.println("去重后：" + result.size());

        StringBuilder json = new StringBuilder();
        json.append("{\"urls\":[");
        for (UrlObject urlObject : urlObjects) {
//            UrlObject reachableObj = reachableUrls.get(i);
            json.append("{\n");
            json.append("  \"name\": \"" + urlObject.getName() + "\",\n");
            json.append("  \"url\": \"" + urlObject.getUrl() + "\"\n");
            json.append("}");
//            if (i < reachableUrls.size() - 1) {
                json.append(",");
//            }
        }
        json.append("]}");
        return json.toString();
    }

    public static void getjson() throws IOException {

        String urlString = "http://tvbox.王二小放牛娃.top/"; // 替换为你的URL
        accessUrl2(urlString, 500);
    }


    public static boolean accessUrl(String url, long timeoutMillis) {
//        String url1 = url;
//        if(containsChinese(url)) {
//             url1 = getPunycode(url);
//        }
        try {
            URL myUrl = new URL(url);
//            URLConnection connection = myUrl.openConnection();
//            //10 秒超时
//            connection.setConnectTimeout((int) timeoutMillis);
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                //System.out.println(inputLine);
//            }
//            in.close();
            HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
            //10秒超时
            connection.setConnectTimeout((int) timeoutMillis);
            connection.setReadTimeout((int) timeoutMillis);
            connection.setRequestMethod("HEAD"); // 使用 HEAD 方法减少数据传输
            int responseCode = connection.getResponseCode();
//            System.out.println("可以访问=====" + url);
            return true;
        } catch (IOException e) {
            System.out.println("不能访问=====" + url);
            return false;
        }
    }


    public static boolean accessUrl2(String url, long timeoutMillis) {
        UrlValidator.UrlCheckResult result = UrlValidator.checkUrl(url);
        return false;
    }

    public static boolean containsChinese(String str) {
        Pattern pattern = Pattern.compile("[\u4E00-\u9FA5]");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static String getPunycode(String url) {
//        String punycodeString;
//        // 将Unicode字符串转换为Punycode字符串
//        punycodeString = Punycode.toPunycode(url);
//        System.out.println("Punycode: " + punycodeString);
//        return punycodeString;
//        String unicodeDomain = "例子.测试";

        String[] strings = url.split("//");
        try {
            String punycode = IDN.toASCII(strings[1]);
//            System.out.println(strings[0] + "//" + punycode); // 输出: http://xn--your-punycode-string-g1a.xn--com-w6g
            return strings[0] + "//" + punycode;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


}

