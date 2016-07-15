package com.wsg.demo.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Jude on 2016/1/6.
 */
public class DataProvider {
    public static List<Person> getPersonList(int page) {
        ArrayList<Person> arr = new ArrayList<>();
        arr.add(new Person("http://i2.hdslb.com/52_52/user/61175/6117592/myface.jpg",
                "月の星く雪" + "————————第" + page + "页",
                "完结来补"));
        arr.add(new Person("http://i1.hdslb.com/52_52/user/6738/673856/myface.jpg", "影·蓝玉", "一看评论被***了一脸，伐开心。"));
        arr.add(new Person("http://i1.hdslb.com/account/face/1467772/e1afaf4a/myface.png", "i琳夏i", "(｀・ω・´)"));
        arr.add(new Person("http://i0.hdslb.com/52_52/user/18494/1849483/myface.jpg", "Minerva。", "为啥下载不能了？π_π"));
        arr.add(new Person("http://i0.hdslb.com/52_52/account/face/4613528/303f4f5a/myface.png", "如歌行极", "求生肉（/TДT)/"));
        arr.add(new Person("http://i0.hdslb.com/52_52/account/face/611203/76c02248/myface.png", "GERM", "第一次看 看弹幕那些说什么影帝模式啥的 感觉日了狗了 让我怎么往后看啊 艹"));
        arr.add(new Person("http://i2.hdslb.com/52_52/user/46230/4623018/myface.jpg", "じ★ve↘魅惑", "开头吾王裙子被撩起来怎么回事！→_→"));
        arr.add(new Person("http://i2.hdslb.com/52_52/user/66723/6672394/myface.jpg", "道尘一梦", "@伪 · 卫宫士郎"));
        arr.add(new Person("http://i1.hdslb.com/user/3039/303946/myface.jpg", "潘多哥斯拉", "朋友，听说过某R吗……..我呸，听说过虫群吗？(｀・ω・´)"));
        arr.add(new Person("http://i2.hdslb.com/account/face/9034989/aabbc52a/myface.png", "一只红发的猫", "道理我都懂，我就问，几楼开车←_←"));
        arr.add(new Person("http://i0.hdslb.com/account/face/1557783/8733bd7b/myface.png", "Mikuの草莓胖次", "扶..扶我起来,喝了最后这一瓶营养快线，让我撸死up"));
        arr.add(new Person("http://i2.hdslb.com/user/3716/371679/myface.jpg", "Absolute Field", "朋也，看过里番吗？"));
        arr.add(new Person("http://i1.hdslb.com/account/face/9045165/4b11d894/myface.png", "琪雅之约", "摩西摩西.警察局么？"));
        return arr;
    }

    public static List<Person> getIndexList() {
        List<Person> persons = new ArrayList<Person>();
        char[] chartable = {'啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈',
                '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌',
                '塌', '挖', '昔', '压', '匝',};
        for (int i = 0; i < 50; i++) {
            Person person = new Person("http://i1.hdslb.com/user/3039/303946/myface.jpg", chartable[(int) (Math.random() * 26)] + "GitHub", "朋友，听说过某R吗……..我呸，听说过虫群吗？(｀・ω・´)");
            persons.add(person);
        }

        return persons;

    }

}
