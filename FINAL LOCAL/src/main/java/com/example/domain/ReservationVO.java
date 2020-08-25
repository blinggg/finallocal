package com.example.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservationVO {
   
   private int reservation_reno;
   private String reservation_id;
   private String users_name;
   
   @JsonFormat(pattern="yyyy-MM-dd kk:mm:ss",timezone ="Asia/Seoul")
   private Date reservation_redate;

   public int getReservation_reno() {
      return reservation_reno;
   }

   public void setReservation_reno(int reservation_reno) {
      this.reservation_reno = reservation_reno;
   }

   public String getReservation_id() {
      return reservation_id;
   }

   public void setReservation_id(String reservation_id) {
      this.reservation_id = reservation_id;
   }

   public String getUsers_name() {
      return users_name;
   }

   public void setUsers_name(String users_name) {
      this.users_name = users_name;
   }

   public Date getReservation_redate() {
      return reservation_redate;
   }

   public void setReservation_redate(Date reservation_redate) {
      this.reservation_redate = reservation_redate;
   }

   @Override
   public String toString() {
      return "ReservationVO [reservation_reno=" + reservation_reno + ", reservation_id=" + reservation_id
            + ", users_name=" + users_name + ", reservation_redate=" + reservation_redate + "]";
   }
   
   
   
   
}