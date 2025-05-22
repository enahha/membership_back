package io.starinc.api.v1.reservation.vo;

import io.starinc.api.v1.common.vo.CommonVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ReservationVo extends CommonVo {
    private int seq;
    private String status;
    private String locale;
    private String name;
    private String wallet_address;
    private String wallet_type;
    private String nft_type;
    private int has_alya;
    private int has_fitri;
    private int has_punkykongz;
    private int has_genesis;
    private String tel_number;
    private String card_number;
    private String email;
    private String card_type;
    private String card_expiry_month;
    private String card_expiry_year;
    private String customer_type;
    private String reservation_service;
    private String room_type;
    private String check_in_date;
    private String check_out_date;
    private String adults;
    private String children;
    private String infants;
    private String extrabed;
    private String breakfast_adults;
    private String breakfast_children;
    private String gold_lounge_adults;
    private String gold_lounge_children;
    private int s_restaurant;
    private String s_restaurant_adults;
    private String s_restaurant_adults_usage_count;
    private String s_restaurant_children;
    private String s_restaurant_children_usage_count;
    private int m_restaurant;
    private String m_restaurant_adults;
    private String m_restaurant_adults_usage_count;
    private String m_restaurant_children;
    private String m_restaurant_children_usage_count;
    private String cancel_time;

}
