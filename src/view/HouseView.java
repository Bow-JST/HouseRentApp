package view;

import domin.House;
import service.HouseService;
import utils.Utility;

/**
 * 1.显示界面
 * 2.接收用户的输入
 * 3.调用HouseService完成对房屋信息的各种操作
 */
public class HouseView {

    //设置一个控制菜单显示的布尔变量
    private boolean loop = true;
    //接收用户的功能选择
    private char key;
    private HouseService houseService = new HouseService(10);//创建一个长度为10的House数组

    //编写updateHouse() 完成修改指定id房屋的信息
    public void updateHouse(){
        System.out.println("============查找房屋信息============");
        //提示用户输入要修改的房屋Id
        System.out.println("请输入待修改的房屋Id(-1表示退出):");
        int updateId = Utility.readInt();

        //设置直接退出方法的语句
        if(updateId == -1){
            System.out.println("============已放弃房屋信息修改============");
            return;
        }

        //查找房屋是否存在
        House house = houseService.find(updateId);
        if(house == null){
            System.out.println("============Id不存在============");
        }
        //提示用户输入修改后的信息
        System.out.println("姓名(" + house.getName() + "):");
        String name = Utility.readString(8, "");
        if(!"".equals(name)){
            house.setName(name);
        }

        System.out.println("电话(" + house.getPhone() + "):");
        String phone = Utility.readString(12,"");
        if(!"".equals(phone)){
            house.setPhone(phone);
        }

        System.out.println("地址(" + house.getAddress() + "):");
        String address = Utility.readString(18, "");
        if(!"".equals(address)){
            house.setAddress(address);
        }

        System.out.println("租金(" + house.getRent() + "):");
        int rent = Utility.readInt(-1);
        if(rent != -1){
            house.setRent(rent);
        }

        System.out.println("状态(" + house.getState() + "):");
        String state = Utility.readString(3, "");
        if(!"".equals(state)){
            house.setState(state);
        }

        System.out.println("============修改房屋信息成功============");

    }
    //编写findHouse() 完成根据id来检索房屋信息
    public void findHouse(){
        System.out.println("============查找房屋信息============");
        System.out.println("请输入房屋的Id:");
        int findId = Utility.readInt();

        //调用HouseService中的find方法
        House house = houseService.find(findId); //因为返回的是House类型的对象，所以应该创建一个House类型的变量来接收

        if(house != null){
            System.out.println(house);
            System.out.println("============查找成功============");
        }else{
            System.out.println("============Id不存在============");
        }


    }
    //编写exitHouse() 完成退出确认
    public void exitHouse(){

        char c = Utility.readConfirmSelection();
        if(c == 'Y'){
            loop = false;
            System.out.println("============退出系统============");
        }else{
            System.out.println("============已取消退出======");
        }
    }
    //编写delHouse() 接收输入的id，调用Service的del方法
    public void delHouse(){
        System.out.println("============删除房屋信息============");
        System.out.println("请输入待删除的房屋编号(-1退出):");
        int delId = Utility.readInt();
        if(delId == -1){
            System.out.println("============放弃删除房屋信息============");
            return;
        }
        //在readConfirmSelection中已经设置了提示和循环
        char select = Utility.readConfirmSelection();
        if(select == 'Y'){
            if(houseService.del(delId)){
                System.out.println("============删除房屋信息成功============");
            }else{
                System.out.println("============房屋编号不存在============");
            }
        }else{
            System.out.println("============放弃删除房屋信息============");
        }

    }
    //编写addHouse() 接收输入，创建House对象，调用add方法
    public void addHouse(){
        System.out.println("============添加房屋============");
        System.out.println("姓名:");
        String name = Utility.readString(8);
        System.out.println("电话:");
        String phone = Utility.readString(12);
        System.out.println("地址:");
        String address = Utility.readString(16);
        System.out.println("月租:");
        int rent = Utility.readInt();
        System.out.println("状态:");
        String state = Utility.readString(3);

        //创建一个新的House对象保存输入的房屋信息
        House newHouse = new House(0, name, phone, address, rent, state);
        if(houseService.add(newHouse)){
            System.out.println("============添加房屋成功============");
        }else{
            System.out.println("============添加房屋失败============");
        }
    }
    //显示房屋列表listHouse
    public void listHouse(){
        System.out.println("============房屋列表============");
        System.out.println("编号\t\t房主\t\t电话\t\t\t地址\t\t月租\t\t房屋状态(未出租/已出租)");
        House [] houses = houseService.list();
        for (int i = 0; i < houses.length; i++){
            if(houses[i] == null){
                break;
            }
            System.out.println(houses[i]);
        }
        System.out.println("============显示完毕============");
    }
    //显示主菜单
    public void mainMenu(){

        do{
            System.out.println("\n============房屋出租系统============");
            System.out.println("\t\t\t1 新增房屋信息");
            System.out.println("\t\t\t2 查找房屋信息");
            System.out.println("\t\t\t3 删除房屋信息");
            System.out.println("\t\t\t4 修改房屋信息");
            System.out.println("\t\t\t5 显示房屋列表");
            System.out.println("\t\t\t6 退出   系统");

            System.out.println("请输入你的选择(1-6):");
            key = Utility.readChar();

            switch (key){
                case '1':
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    updateHouse();
                    break;
                case '5':
                    listHouse();
                    break;
                case '6':
                    exitHouse();
                    break;
                default:
                    System.out.println("功能选择有误，请重新选择!");
                    break;
            }

        }while(loop);
    }

}
