package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private HashMap<String, Order> orderMap;
    private HashMap<String, DeliveryPartner> partnerMap;
    private HashMap<String, HashSet<String>> partnerToOrderMap;
    private HashMap<String, String> orderToPartnerMap;

    public OrderRepository(){
        this.orderMap = new HashMap<String, Order>();
        this.partnerMap = new HashMap<String, DeliveryPartner>();
        this.partnerToOrderMap = new HashMap<String, HashSet<String>>();
        this.orderToPartnerMap = new HashMap<String, String>();
    }

    public void saveOrder(Order order){
        // your code here
        orderMap.put(order.getId(),order);
    }

    public void savePartner(String partnerId){
        // your code here
        // create a new partner with given partnerId and save it
        DeliveryPartner partner = new DeliveryPartner(partnerId);
        partnerMap.put(partnerId,partner);


    }

    public void saveOrderPartnerMap(String orderId, String partnerId){
        if(orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)){
            // your code here
            //add order to given partner's order list
            //increase order count of partner
            //assign partner to this order
            orderToPartnerMap.put(orderId,partnerId);
            partnerToOrderMap.computeIfAbsent(partnerId, k -> new HashSet<>()).add(orderId);
            DeliveryPartner partner = partnerMap.get(partnerId);
            partner.increatement();

        }
    }

    public Order findOrderById(String orderId){
        // your code here
      return orderMap.get(orderId);
    }

    public DeliveryPartner findPartnerById(String partnerId){
        // your code here
        return partnerMap.get(partnerId);
    }

    public Integer findOrderCountByPartnerId(String partnerId){
        // your code here
        return partnerMap.containsKey(partnerId) ? partnerMap.get(partnerId).getNumberOfOrders() : 0;
    }

    public List<String> findOrdersByPartnerId(String partnerId){
        // your code here
        return new ArrayList<>(partnerToOrderMap.getOrDefault(partnerId, new HashSet<>()));
    }

    public List<String> findAllOrders(){
        // your code here
        // return list of all orders
       return new ArrayList<>(orderMap.keySet());
    }

    public void deletePartner(String partnerId){
        // your code here
        // delete partner by ID
        partnerMap.remove(partnerId);
    }

    public void deleteOrder(String orderId){
        // your code here
        // delete order by ID
        if (orderToPartnerMap.containsKey(orderId)) {
            String partnerId = orderToPartnerMap.get(orderId);
            partnerToOrderMap.get(partnerId).remove(orderId);
            orderToPartnerMap.remove(orderId);
        }
        orderMap.remove(orderId);
    }

    public Integer findCountOfUnassignedOrders(){
        // your code here
        int unassign =0;
        for(String orderId : orderMap.keySet()){
            if(!orderToPartnerMap.containsKey(orderId)){
                unassign++;
            }
        }
        return unassign;
    }

    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String timeString, String partnerId){
        // your code here
        int time = convertTimeStringToMinutes(timeString);
        int ordersLeft = 0;
        if (partnerToOrderMap.containsKey(partnerId)) {
            for (String orderId : partnerToOrderMap.get(partnerId)) {
                Order order = orderMap.get(orderId);
                if (order != null && order.getDeliveryTime() > time) {
                    ordersLeft++;
                }
            }
        }
        return ordersLeft;
    }

    public String findLastDeliveryTimeByPartnerId(String partnerId){
        // your code here
        // code should return string in format HH:MM
        int lastDeliveryTime = Integer.MIN_VALUE;
        if (partnerToOrderMap.containsKey(partnerId)) {
            for (String orderId : partnerToOrderMap.get(partnerId)) {
                Order order = orderMap.get(orderId);
                if (order != null && order.getDeliveryTime() > lastDeliveryTime) {
                    lastDeliveryTime = order.getDeliveryTime();
                }
            }
        }
        return convertMinutesToTimeString(lastDeliveryTime);
    }
    public int convertTimeStringToMinutes(String time){
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    public String convertMinutesToTimeString(int time){
        int hours = time / 60;
        int mins = time % 60;
        return String.format("%02d:%02d", hours, mins);
    }
}