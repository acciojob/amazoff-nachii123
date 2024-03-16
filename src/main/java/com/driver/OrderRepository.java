//package com.driver;
//
//import java.util.*;
//
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class OrderRepository {
//
//    private HashMap<String, Order> orderMap;
//    private HashMap<String, DeliveryPartner> partnerMap;
//    private HashMap<String, HashSet<String>> partnerToOrderMap;
//    private HashMap<String, String> orderToPartnerMap;
//
//    public OrderRepository(){
//        this.orderMap = new HashMap<String, Order>();
//        this.partnerMap = new HashMap<String, DeliveryPartner>();
//        this.partnerToOrderMap = new HashMap<String, HashSet<String>>();
//        this.orderToPartnerMap = new HashMap<String, String>();
//    }
//
//    public void saveOrder(Order order){
//        // your code here
//        if (order != null) {
//            orderMap.put(order.getId(), order);
//        }
//    }
//
//    public void savePartner(String partnerId){
//        // your code here
//        // create a new partner with given partnerId and save it
////        DeliveryPartner partner = new DeliveryPartner(partnerId);
////        partnerMap.put(partnerId,partner);
//        if (partnerId != null && !partnerMap.containsKey(partnerId)) {
//            DeliveryPartner partner = new DeliveryPartner(partnerId);
//            partnerMap.put(partnerId, partner);
//        }
//
//
//    }
//
//    public void saveOrderPartnerMap(String orderId, String partnerId){
//        if(orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)){
//            DeliveryPartner partner = partnerMap.get(partnerId);
//            partner.incrementOrderCount();
//            orderToPartnerMap.put(orderId, partnerId);
//            if (!partnerToOrderMap.containsKey(partnerId)) {
//                partnerToOrderMap.put(partnerId, new HashSet<>());
//            }
//            partnerToOrderMap.get(partnerId).add(orderId);
//        }
//    }
//
//    public Order findOrderById(String orderId){
//        // your code here
//         if (orderMap.containsKey(orderId)) {
//                    return orderMap.get(orderId);
//                }
//                return null;
//    }
//
//    public DeliveryPartner findPartnerById(String partnerId){
//        // your code here
//        if (partnerMap.containsKey(partnerId)) {
//            return partnerMap.get(partnerId);
//        }
//        return null;
//    }
//
//    public Integer findOrderCountByPartnerId(String partnerId){
//        // your code here
//        if (partnerMap.containsKey(partnerId)) {
//            DeliveryPartner partner = partnerMap.get(partnerId);
//            return partner.getNumberOfOrders();
//        }
//        return 0;
//    }
//
//    public List<String> findOrdersByPartnerId(String partnerId){
//        // your code here
//        if (partnerToOrderMap.containsKey(partnerId)) {
//            return new ArrayList<>(partnerToOrderMap.get(partnerId));
//        }
//        return new ArrayList<>();
//    }
//
//    public List<String> findAllOrders(){
//        // your code here
//        // return list of all orders
//       return new ArrayList<>(orderMap.keySet());
//    }
//
//    public void deletePartner(String partnerId){
//        // your code here
//        // delete partner by ID
//        if (partnerMap.containsKey(partnerId)) {
//            partnerMap.remove(partnerId);
//            if (partnerToOrderMap.containsKey(partnerId)) {
//                partnerToOrderMap.remove(partnerId);
//            }
//        }
//    }
//
//    public void deleteOrder(String orderId){
//        // your code here
//        // delete order by ID
//        if (orderMap.containsKey(orderId)) {
//            String partnerId = orderToPartnerMap.get(orderId);
//            if (partnerToOrderMap.containsKey(partnerId)) {
//                partnerToOrderMap.get(partnerId).remove(orderId);
//            }
//            orderMap.remove(orderId);
//            orderToPartnerMap.remove(orderId);
//        }
//    }
//
//    public Integer findCountOfUnassignedOrders(){
//        // your code here
//        int unassignedCount = 0;
//        for (String orderId : orderMap.keySet()) {
//            if (!orderToPartnerMap.containsKey(orderId)) {
//                unassignedCount++;
//            }
//        }
//        return unassignedCount;
//    }
//
//    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String timeString, String partnerId){
//        // your code here
//        if (partnerMap.containsKey(partnerId)) {
//            int count = 0;
//            int givenTime = convertTimeStringToMinutes(timeString);
//            HashSet<String> orders = partnerToOrderMap.get(partnerId);
//            if (orders != null) {
//                for (String orderId : orders) {
//                    Order order = orderMap.get(orderId);
//                    if (order != null && order.getDeliveryTime() > givenTime) {
//                        count++;
//                    }
//                }
//            }
//            return count;
//        }
//        return 0;
//    }
//
//    public String findLastDeliveryTimeByPartnerId(String partnerId){
//        // your code here
//        // code should return string in format HH:MM
//        if (partnerMap.containsKey(partnerId)) {
//            int lastDeliveryTime = 0;
//            HashSet<String> orders = partnerToOrderMap.get(partnerId);
//            if (orders != null) {
//                for (String orderId : orders) {
//                    Order order = orderMap.get(orderId);
//                    if (order != null && order.getDeliveryTime() > lastDeliveryTime) {
//                        lastDeliveryTime = order.getDeliveryTime();
//                    }
//                }
//            }
//            int hours = lastDeliveryTime / 60;
//            int minutes = lastDeliveryTime % 60;
//            return String.format("%02d:%02d", hours, minutes);
//        }
//        return "";
//    }
//    public int convertTimeStringToMinutes(String time){
//        String[] parts = time.split(":");
//        int hours = Integer.parseInt(parts[0]);
//        int minutes = Integer.parseInt(parts[1]);
//        return hours * 60 + minutes;
//    }
//
//    public String convertMinutesToTimeString(int time){
//        int hours = time / 60;
//        int mins = time % 60;
//        return String.format("%02d:%02d", hours, mins);
//    }
//}


package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private HashMap<String, Order> orderMap;
    private HashMap<String, DeliveryPartner> partnerMap;
    private HashMap<String, HashSet<String>> partnerToOrderMap;
    private HashMap<String, String> orderToPartnerMap;

    public OrderRepository() {
        this.orderMap = new HashMap<>();
        this.partnerMap = new HashMap<>();
        this.partnerToOrderMap = new HashMap<>();
        this.orderToPartnerMap = new HashMap<>();
    }

    public void saveOrder(Order order) {
        if (order != null) {
            orderMap.put(order.getId(), order);
        }
    }

    public void savePartner(String partnerId) {
        if (partnerId != null && !partnerId.isEmpty()) {
            DeliveryPartner partner = new DeliveryPartner(partnerId);
            partnerMap.put(partnerId, partner);
        }
    }

    public void saveOrderPartnerMap(String orderId, String partnerId) {
        if (orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)) {
            orderToPartnerMap.put(orderId, partnerId);
            partnerToOrderMap.computeIfAbsent(partnerId, k -> new HashSet<>()).add(orderId);
            DeliveryPartner partner = partnerMap.get(partnerId);
            partner.incrementOrderCount();
        }
    }

    public Order findOrderById(String orderId) {
        return orderMap.get(orderId);
    }

    public DeliveryPartner findPartnerById(String partnerId) {
        return partnerMap.get(partnerId);
    }

    public Integer findOrderCountByPartnerId(String partnerId) {
        DeliveryPartner partner = partnerMap.get(partnerId);
        return partner != null ? partner.getNumberOfOrders() : 0;
    }

    public List<String> findOrdersByPartnerId(String partnerId) {
        return new ArrayList<>(partnerToOrderMap.getOrDefault(partnerId, new HashSet<>()));
    }

    public List<String> findAllOrders() {
        return new ArrayList<>(orderMap.keySet());
    }

    public Integer findCountOfUnassignedOrders() {
        int unassignedCount = 0;
        for (Order order : orderMap.values()) {
            if (!orderToPartnerMap.containsKey(order.getId())) {
                unassignedCount++;
            }
        }
        return unassignedCount;
    }

    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String timeString, String partnerId) {
        int count = 0;
        int givenTime = convertTimeStringToMinutes(timeString);
        HashSet<String> orders = partnerToOrderMap.getOrDefault(partnerId, new HashSet<>());
        for (String orderId : orders) {
            Order order = orderMap.get(orderId);
            if (order != null && order.getDeliveryTime() > givenTime) {
                count++;
            }
        }
        return count;
    }

    public String findLastDeliveryTimeByPartnerId(String partnerId) {
        String lastDeliveryTime = "";
        int maxTime = Integer.MIN_VALUE;
        HashSet<String> orders = partnerToOrderMap.getOrDefault(partnerId, new HashSet<>());
        for (String orderId : orders) {
            Order order = orderMap.get(orderId);
            if (order != null && order.getDeliveryTime() > maxTime) {
                maxTime = order.getDeliveryTime();
                int hours = maxTime / 60;
                int minutes = maxTime % 60;
                lastDeliveryTime = String.format("%02d:%02d", hours, minutes);
            }
        }
        return lastDeliveryTime;
    }

    public void deletePartner(String partnerId) {
        if (partnerId != null) {
            partnerMap.remove(partnerId);
            HashSet<String> orders = partnerToOrderMap.remove(partnerId);
            if (orders != null) {
                for (String orderId : orders) {
                    orderToPartnerMap.remove(orderId);
                }
            }
        }
    }

    public void deleteOrder(String orderId) {
        if (orderId != null) {
            String partnerId = orderToPartnerMap.remove(orderId);
            if (partnerId != null) {
                HashSet<String> orders = partnerToOrderMap.get(partnerId);
                if (orders != null) {
                    orders.remove(orderId);
                }
            }
            orderMap.remove(orderId);
        }
    }

    private int convertTimeStringToMinutes(String timeString) {
        String[] parts = timeString.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
}