package org.example.service;
import org.example.model.Fish;
import org.example.model.Product;
import org.example.model.UserAccount;
import org.example.repository.FishRepo;
import org.example.repository.ProductRepo;

public class FishService {

    public void createConfirm(UserAccount id) {
        org.example.repository.FishRepo fishRepo = new org.example.repository.FishRepo();
        Fish fish = new Fish(id);
        fishRepo.createConfirm(fish);
    }
    public void changeConfirm(int id, int confirm) {
        FishRepo fishRepo = new FishRepo();
        Fish fish1 = fishRepo.findConfirmFromConfirmTable(id);
        UserAccount userAccount = new UserAccount(id);
        fish1.setUserAccount(userAccount);
        if (fish1 != null) {
            if (confirm == 1) {
                fish1.setConfirm("Yes");
                fishRepo.updateConfirm(fish1, 1);
                System.out.println("Confirm Changed to 'Yes'");
            } else
                System.out.println("Confirm is Already 'Yes'!");
        } else if (confirm == 2) {
            fish1.setConfirm("No");
            fishRepo.updateConfirm(fish1, 2);
            System.out.println("Confirm Changed to 'No'");
        } else {
            System.out.println("Confirm is Already 'No'!");
        }
    }

    public void changeConfirmAgain(int id,int confirm) {
        FishService fishService = new FishService();
        fishService.changeConfirm(id, confirm);
    }


    public void updatetotalPrice(int totalPrice , int Id) {
        FishRepo fishRepo = new FishRepo();
        fishRepo.updateTotalPrice(totalPrice,Id);
    }

    public void totalPriceForUser(int barcode, int number , int id){
        FishService fishService = new FishService();
        ProductRepo productRepo = new ProductRepo();
        Product price = productRepo.findDataForProduct(barcode);
        number = number * price.getPrice();
        fishService.updatetotalPrice(number,id);
    }
}
