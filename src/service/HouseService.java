package service;

import domin.House;

/**
 * 1.响应HouseView的调用
 * 2.完成对房屋信息的各种操作(crud)[create read update delete]
 */
public class HouseService {


    private House[] houses; //创建一个House的数组保存信息
    private int houseNums = 1; //记录数组中当前存在多少个房屋信息
    private int idCounter = 1; //记录当前的id增长到哪个值

    //通过构造器指定数组的大小
    public HouseService(int size){
        houses = new House[size];
        houses[0] = new House(1,"张先生","18004429639","北京",2000,"未出租");
    }

    //find方法，查找房屋信息
    public House find(int findId){
        //遍历数组查找Id
        for (int i = 0; i < houseNums; i++) {
            if(findId == houses[i].getId()){
                return houses[i];
            }
        }

        //如果没有找到对象Id的房屋信息就返回空
        return null;
    }

    //del方法，删除房屋信息
    public boolean del(int delId){
        //先找到待删除的房屋信息
        int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if(houses[i].getId() == delId){
                index = i;
            }
        }
        if(index == -1){
            return false;
        }

        //通过循环覆盖掉要删除的数据
        for (int i = index; i < houseNums - 1; i++) {
            houses[i] = houses[i + 1];
        }

        //数组变化
        houses[--houseNums] = null;


        return true;



    }
    //add方法，添加新的对象到House的数组中，返回boolean
    public boolean add(House newHouse){
        //判断数组是否可以继续添加
        if(houseNums == houses.length ){
            System.out.println("数组已满，不能在进行添加");
            return false;
        }
        //后++，先使用后++
        houses[houseNums++] = newHouse;
        //设计id自增长机制
        /*
        对于这个Id是设置在House类中的一个属性，但是我们知道
        房屋编号采用添加记录的前后顺序来递增管理，所以在HouseView
        的方法中，输入房屋信息时不进行id的输入，那么为什么不直接用
        数组的索引来代替Id呢？因为房屋信息可能会删除，那么利用索引
        设置的房屋Id就会改变，这样不方便房屋信息的管理
         */

        idCounter++;
        newHouse.setId(idCounter);
        return true;
    }
    //返回所有的数组
    public House[] list(){
        return houses;
    }
}
