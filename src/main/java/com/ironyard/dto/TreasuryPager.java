//package com.ironyard.dto;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//
//import java.util.List;
//
///**
// * Created by nathanielellsworth on 11/28/16.
// */
//public class TreasuryPager extends PageImpl<TreasuryBills> {
//    static class CustomPage {
//
//        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
//        public CustomPage(@JsonProperty("content") List<TreasuryBills> content, @JsonProperty("number") int page, @JsonProperty("size") int size, @JsonProperty("totalElements") long total) {
//            super(content, new PageRequest(page, size), total);
//        }
//
//    }
//
//    public Page<TreasuryBills> makeRequest(String json) {
//
//        Page<TreasuryBills> pg = new ObjectMapper().readValue(json, TreasuryPager.class);
//        return pg;
//
//    }
//}
