package com.donlim.pm.em;

import com.changhong.sei.annotation.Remark;

/**
 * @author Carol
 * @date 2022/8/15 16:16
 * @description
 */
public enum EmpstatidEnum {

    // 0、RECRUIT招募待入职  1、实习PRACTICE  2、试用TRY  3、考察INSPECT 4、正式OFFICIAL 5、借出BORROW
    // 6、TO_BE_HIRED待入职  7、实习试用PRACTICE_TRY  8、PROBATION见习期  9、/ 10、/ 11、TO_BE_LEAVE待离职
    // 12、离职LEAVE  13、黑名单BLACK
    @Remark("招募待入职")
    RECRUIT,
    @Remark("实习")
    PRACTICE,
    @Remark("试用")
    TRY,
    @Remark("考察")
    INSPECT,
    @Remark("正式")
    OFFICIAL,
    @Remark("借出")
    BORROW,
    @Remark("待入职")
    TO_BE_HIRED,
    @Remark("实习试用")
    PRACTICE_TRY,
    @Remark("见习期")
    PROBATION,
    @Remark("预留字段1")
    REMARK1,
    @Remark("预留字段2")
    REMARK2,
    @Remark("待离职")
    TO_BE_LEAVE,
    @Remark("离职")
    LEAVE,
    @Remark("黑名单")
    BLACK;

}
