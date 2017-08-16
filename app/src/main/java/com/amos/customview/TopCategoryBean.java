package com.amos.customview;

import java.util.List;

/**
 * Created by Amos on 2017/8/15.
 * Desc：
 */

public class TopCategoryBean {

    /**
     * male : [{"name":"玄幻","bookCount":463947},{"name":"奇幻","bookCount":43167},{"name":"武侠","bookCount":38139},{"name":"仙侠","bookCount":122667},{"name":"都市","bookCount":326233},{"name":"职场","bookCount":14846},{"name":"历史","bookCount":65114},{"name":"军事","bookCount":14065},{"name":"游戏","bookCount":76036},{"name":"竞技","bookCount":5356},{"name":"科幻","bookCount":105012},{"name":"灵异","bookCount":31075},{"name":"同人","bookCount":36167},{"name":"轻小说","bookCount":4662}]
     * female : [{"name":"古代言情","bookCount":402183},{"name":"现代言情","bookCount":496521},{"name":"青春校园","bookCount":105053},{"name":"纯爱","bookCount":123325},{"name":"玄幻奇幻","bookCount":120662},{"name":"武侠仙侠","bookCount":61600},{"name":"科幻","bookCount":8674},{"name":"游戏竞技","bookCount":5734},{"name":"悬疑灵异","bookCount":13096},{"name":"同人","bookCount":122299},{"name":"女尊","bookCount":20548},{"name":"莉莉","bookCount":24197}]
     * picture : [{"name":"热血","bookCount":312},{"name":"魔幻","bookCount":326},{"name":"科幻","bookCount":64},{"name":"恋爱","bookCount":520},{"name":"搞笑","bookCount":479},{"name":"悬疑","bookCount":150},{"name":"少儿","bookCount":2555}]
     * press : [{"name":"传记名著","bookCount":2334},{"name":"出版小说","bookCount":5046},{"name":"人文社科","bookCount":11133},{"name":"生活时尚","bookCount":940},{"name":"经管理财","bookCount":4356},{"name":"青春言情","bookCount":4481},{"name":"外文原版","bookCount":631},{"name":"政治军事","bookCount":282},{"name":"成功励志","bookCount":4292},{"name":"育儿健康","bookCount":3722}]
     * ok : true
     */


    private boolean ok;
    private List<MaleBean> male;
    private List<MaleBean> female;
    private List<MaleBean> picture;
    private List<MaleBean> press;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<MaleBean> getMale() {
        return male;
    }

    public void setMale(List<MaleBean> male) {
        this.male = male;
    }

    public List<MaleBean> getFemale() {
        return female;
    }

    public void setFemale(List<MaleBean> female) {
        this.female = female;
    }

    public List<MaleBean> getPicture() {
        return picture;
    }

    public void setPicture(List<MaleBean> picture) {
        this.picture = picture;
    }

    public List<MaleBean> getPress() {
        return press;
    }

    public void setPress(List<MaleBean> press) {
        this.press = press;
    }

    public static class MaleBean {
        /**
         * name : 玄幻
         * bookCount : 463947
         */

        private String name;
        private int bookCount;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBookCount() {
            return bookCount;
        }

        public void setBookCount(int bookCount) {
            this.bookCount = bookCount;
        }

        @Override
        public String toString() {
            return "MaleBean{" +
                    "name='" + name + '\'' +
                    ", bookCount=" + bookCount +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "TopCategoryBean{" +
                "ok=" + ok +
                ", male=" + male +
                ", female=" + female +
                ", picture=" + picture +
                ", press=" + press +
                '}';
    }
}
