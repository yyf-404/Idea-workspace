package com.yyf.mallcache.bean;

public class Car {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_car.Car_id
     *
     * @mbggenerated Fri Aug 02 11:29:41 CST 2019
     */
    private Integer carId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_car.car_name
     *
     * @mbggenerated Fri Aug 02 11:29:41 CST 2019
     */
    private String carName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_car.Car_id
     *
     * @return the value of mall_car.Car_id
     *
     * @mbggenerated Fri Aug 02 11:29:41 CST 2019
     */
    public Integer getCarId() {
        return carId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_car.Car_id
     *
     * @param carId the value for mall_car.Car_id
     *
     * @mbggenerated Fri Aug 02 11:29:41 CST 2019
     */
    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_car.car_name
     *
     * @return the value of mall_car.car_name
     *
     * @mbggenerated Fri Aug 02 11:29:41 CST 2019
     */
    public String getCarName() {
        return carName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_car.car_name
     *
     * @param carName the value for mall_car.car_name
     *
     * @mbggenerated Fri Aug 02 11:29:41 CST 2019
     */
    public void setCarName(String carName) {
        this.carName = carName == null ? null : carName.trim();
    }
}