package cn.godzilla.dao;

import cn.godzilla.model.PropBill;

public interface PropBillMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table properties_bill
     *
     * @mbggenerated Sat Oct 31 16:41:34 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table properties_bill
     *
     * @mbggenerated Sat Oct 31 16:41:34 CST 2015
     */
    int insert(PropBill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table properties_bill
     *
     * @mbggenerated Sat Oct 31 16:41:34 CST 2015
     */
    int insertSelective(PropBill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table properties_bill
     *
     * @mbggenerated Sat Oct 31 16:41:34 CST 2015
     */
    PropBill selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table properties_bill
     *
     * @mbggenerated Sat Oct 31 16:41:34 CST 2015
     */
    int updateByPrimaryKeySelective(PropBill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table properties_bill
     *
     * @mbggenerated Sat Oct 31 16:41:34 CST 2015
     */
    int updateByPrimaryKey(PropBill record);
}