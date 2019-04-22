package com.nguyenminh.mvpexample.model;

import com.nguyenminh.mvpexample.model.entity.ItemImage;

import java.util.ArrayList;
import java.util.List;

public class DataImage {
    private LoadData loadData;
    private List<ItemImage> listDemo = new ArrayList<>();
    public DataImage(LoadData loadData) {
        this.loadData = loadData;
    }

    public void createListData(){

        ItemImage demo = new ItemImage("https://thuypetpro.com.vn/wp-content/uploads/2017/07/co-nen-cat-duoi-meo-hay-khong.jpg","Meo meo");
        ItemImage demo1 = new ItemImage("https://s1.bloganchoi.com/wp-content/uploads/2016/08/vu-tru.jpg","BA VT");
        ItemImage demo2 = new ItemImage("http://khoahocphattrien.vn/Images/Uploaded/Share/2017/10/29/341.jpg","Vũ trụ");
        ItemImage demo3 = new ItemImage("https://i.khoahoc.tv/photos/image/2015/09/17/chet_ngoai_vu_tru.jpg","Phi hành gia");
        ItemImage demo4 = new ItemImage("https://img.thuthuatphanmem.vn/uploads/2018/09/24/anh-doremon-bong-bay_054125327.jpg","Doremon");
        ItemImage demo5 = new ItemImage("https://i.ytimg.com/vi/ZdyRer_g4xA/hqdefault.jpg","Thủy thủ mặt trăng");
        ItemImage demo6 = new ItemImage("http://teky.edu.vn/wp-content/uploads/sites/4/2018/08/robot3.jpeg","Robot");
        ItemImage demo7 = new ItemImage("https://kenh14cdn.com/2017/photo-2-1510459495605.jpg","Destroy");
        ItemImage demo8 = new ItemImage("https://vevietnamairline.com/Img.ashx?636547984689865774.jpg","Pháp");
        ItemImage demo9 = new ItemImage("http://media.vietq.vn/files/lelan/2016/12/25/huong-duong-1-25-12-2016.jpg","Hoa hướng dương");
        ItemImage demo10 = new ItemImage("https://cayvahoa.net/wp-content/uploads/2016/07/xuong-rong-bong-vang.jpg","Xương rồng");
        listDemo.add(demo);
        listDemo.add(demo1);
        listDemo.add(demo2);
        listDemo.add(demo3);
        listDemo.add(demo4);
        listDemo.add(demo5);
        listDemo.add(demo6);
        listDemo.add(demo7);
        listDemo.add(demo8);
        listDemo.add(demo9);
        listDemo.add(demo10);

        loadData.onLoadDataSuccess(listDemo);
    }

    public void createListData1(){

        ItemImage demo = new ItemImage("https://images.pexels.com/photos/906150/pexels-photo-906150.jpeg?cs=srgb&dl=beautiful-bloom-blossom-906150.jpg&fm=jpg","Meo meo");
        ItemImage demo1 = new ItemImage("https://images.pexels.com/photos/906150/pexels-photo-906150.jpeg?cs=srgb&dl=beautiful-bloom-blossom-906150.jpg&fm=jpg","BA VT");
        ItemImage demo2 = new ItemImage("https://images.pexels.com/photos/906150/pexels-photo-906150.jpeg?cs=srgb&dl=beautiful-bloom-blossom-906150.jpg&fm=jpg","Vũ trụ");
        ItemImage demo3 = new ItemImage("https://images.pexels.com/photos/906150/pexels-photo-906150.jpeg?cs=srgb&dl=beautiful-bloom-blossom-906150.jpg&fm=jpg","Phi hành gia");
        ItemImage demo4 = new ItemImage("https://images.pexels.com/photos/906150/pexels-photo-906150.jpeg?cs=srgb&dl=beautiful-bloom-blossom-906150.jpg&fm=jpg","Doremon");
        ItemImage demo5 = new ItemImage("https://avatars2.githubusercontent.com/u/28?v=4","Thủy thủ mặt trăng");
        ItemImage demo6 = new ItemImage("https://avatars2.githubusercontent.com/u/28?v=4","Robot");
        ItemImage demo7 = new ItemImage("https://avatars2.githubusercontent.com/u/28?v=4","Destroy");
        ItemImage demo8 = new ItemImage("https://avatars2.githubusercontent.com/u/28?v=4","Pháp");
        ItemImage demo9 = new ItemImage("https://avatars2.githubusercontent.com/u/28?v=4","Hoa hướng dương");
        ItemImage demo10 = new ItemImage("https://avatars2.githubusercontent.com/u/28?v=4","Xương rồng");
        listDemo.add(demo);
        listDemo.add(demo1);
        listDemo.add(demo2);
        listDemo.add(demo3);
        listDemo.add(demo4);
        listDemo.add(demo5);
        listDemo.add(demo6);
        listDemo.add(demo7);
        listDemo.add(demo8);
        listDemo.add(demo9);
        listDemo.add(demo10);

        loadData.onLoadDataSuccess(listDemo);
    }

    public void createListData2(){

        ItemImage demo = new ItemImage("https://vevietnamairline.com/Img.ashx?636547984689865774.jpg","Meo meo");
        ItemImage demo1 = new ItemImage("https://thuypetpro.com.vn/wp-content/uploads/2017/07/co-nen-cat-duoi-meo-hay-khong.jpg","BA VT");
        ItemImage demo2 = new ItemImage("https://vevietnamairline.com/Img.ashx?636547984689865774.jpg","Vũ trụ");
        ItemImage demo3 = new ItemImage("https://thuypetpro.com.vn/wp-content/uploads/2017/07/co-nen-cat-duoi-meo-hay-khong.jpg","Phi hành gia");
        ItemImage demo4 = new ItemImage("https://vevietnamairline.com/Img.ashx?636547984689865774.jpg","Doremon");
        ItemImage demo5 = new ItemImage("https://thuypetpro.com.vn/wp-content/uploads/2017/07/co-nen-cat-duoi-meo-hay-khong.jpg","Thủy thủ mặt trăng");
        ItemImage demo6 = new ItemImage("https://vevietnamairline.com/Img.ashx?636547984689865774.jpg","Robot");
        ItemImage demo7 = new ItemImage("https://thuypetpro.com.vn/wp-content/uploads/2017/07/co-nen-cat-duoi-meo-hay-khong.jpg","Destroy");
        ItemImage demo8 = new ItemImage("https://vevietnamairline.com/Img.ashx?636547984689865774.jpg","Pháp");
        ItemImage demo9 = new ItemImage("https://thuypetpro.com.vn/wp-content/uploads/2017/07/co-nen-cat-duoi-meo-hay-khong.jpg","Hoa hướng dương");
        ItemImage demo10 = new ItemImage("https://vevietnamairline.com/Img.ashx?636547984689865774.jpg","Xương rồng");
        listDemo.add(demo);
        listDemo.add(demo1);
        listDemo.add(demo2);
        listDemo.add(demo3);
        listDemo.add(demo4);
        listDemo.add(demo5);
        listDemo.add(demo6);
        listDemo.add(demo7);
        listDemo.add(demo8);
        listDemo.add(demo9);
        listDemo.add(demo10);

        loadData.onLoadDataSuccess(listDemo);
    }
}
