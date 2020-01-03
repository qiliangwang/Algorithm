package test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author wangql
 * @since 2019/11/28 5:45 下午
 */
public class SortTest {
    public static void main(String[] args) {

        List<PackageItem> list = new ArrayList<>();

        for (Long i = 0L; i < 1000L; i ++) {
            PackageItem packageItem = new PackageItem();
            packageItem.setPackageItemId(286);
            packageItem.setExpireAt(i);
            list.add(packageItem);
        }



        Integer num = 286;
        PackageItem res = list
                .stream()
                .filter(packageItem -> num.equals(packageItem.getPackageItemId()))
                .sorted(Comparator.comparing(PackageItem::getExpireAt).reversed())
                .findFirst()
                .orElse(null);

        System.out.println(res);
        System.out.println(new Date().getTime());



    }
}
