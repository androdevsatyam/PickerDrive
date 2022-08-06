package com.model;

public class FormModel {
    String status, msg, name, date, loadingDate, mobile, address, company_name, car_name, vehicle_weight_capicity, vehicle_no, vehicle_type, material_detail, material_weight, storage_time, transport_type, from, to, requirement,labour_requirement,labour_available;


    public FormModel(String name, String date, String loadingDate, String mobile, String address, String company_name, String car_name, String vehicle_weight_capicity, String vehicle_no, String vehicle_type, String labour_available, String labour_requirement, String material_detail, String material_weight, String storage_time, String transport_type, String from, String to, String requirement) {
        this.name = name;
        this.date = date;
        this.loadingDate = loadingDate;
        this.mobile = mobile;
        this.address = address;
        this.company_name = company_name;
        this.car_name = car_name;
        this.vehicle_weight_capicity = vehicle_weight_capicity;
        this.vehicle_no = vehicle_no;
        this.vehicle_type = vehicle_type;
        this.labour_available = labour_available;
        this.labour_requirement = labour_requirement;
        this.material_detail = material_detail;
        this.material_weight = material_weight;
        this.storage_time = storage_time;
        this.transport_type = transport_type;
        this.from = from;
        this.to = to;
        this.requirement = requirement;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(String loadingDate) {
        this.loadingDate = loadingDate;
    }

    public String getLabour_requirement() {
        return labour_requirement;
    }

    public String getLabour_available() {
        return labour_available;
    }

    public String getMOBILE() {
        return mobile;
    }

    public void setMOBILE(String MOBILE) {
        this.mobile = MOBILE;
    }

    public String getADDRESS() {
        return address;
    }

    public void setADDRESS(String ADDRESS) {
        this.address = ADDRESS;
    }

    public String getCOMPANY_NAME() {
        return company_name;
    }

    public void setCOMPANY_NAME(String COMPANY_NAME) {
        this.company_name = COMPANY_NAME;
    }

    public String getCAR_NAME() {
        return car_name;
    }

    public void setCAR_NAME(String CAR_NAME) {
        this.car_name = CAR_NAME;
    }

    public String getVEHICLE_WEIGHT_CAPICITY() {
        return vehicle_weight_capicity;
    }

    public void setVEHICLE_WEIGHT_CAPICITY(String VEHICLE_WEIGHT_CAPICITY) {
        this.vehicle_weight_capicity = VEHICLE_WEIGHT_CAPICITY;
    }

    public String getVEHICLE_NO() {
        return vehicle_no;
    }

    public void setVEHICLE_NO(String VEHICLE_NO) {
        this.vehicle_no = VEHICLE_NO;
    }

    public String getVEHICLE_TYPE() {
        return vehicle_type;
    }

    public void setVEHICLE_TYPE(String VEHICLE_TYPE) {
        this.vehicle_type = VEHICLE_TYPE;
    }

    public String getMATERIAL_DETAIL() {
        return material_detail;
    }

    public void setMATERIAL_DETAIL(String MATERIAL_DETAIL) {
        this.material_detail = MATERIAL_DETAIL;
    }

    public String getMATERIAL_WEIGHT() {
        return material_weight;
    }

    public void setMATERIAL_WEIGHT(String MATERIAL_WEIGHT) {
        this.material_weight = MATERIAL_WEIGHT;
    }

    public String getSTORAGE_TIME() {
        return storage_time;
    }

    public void setSTORAGE_TIME(String STORAGE_TIME) {
        this.storage_time = STORAGE_TIME;
    }

    public String getTRANSPORT_TYPE() {
        return transport_type;
    }

    public void setTRANSPORT_TYPE(String TRANSPORT_TYPE) {
        this.transport_type = TRANSPORT_TYPE;
    }

    public String getFROM() {
        return from;
    }

    public void setFROM(String FROM) {
        this.from = FROM;
    }

    public String getTO() {
        return to;
    }

    public void setTO(String TO) {
        this.to = TO;
    }

    public String getREQUIREMENT() {
        return requirement;
    }

    public void setREQUIREMENT(String REQUIREMENT) {
        this.requirement = REQUIREMENT;
    }
}
