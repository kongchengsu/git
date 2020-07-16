package com.jtexplorer.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 苏空城
 * @since 2020-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "exam_id", type = IdType.AUTO)
    private Long examId;

    private String examName;

    /**
     * 考试开始时间
     */
    private Date examStartTime;

    /**
     * 考试结束时间
     */
    private Date examEndTime;

    private String examRemark;

    private Date examCreateTime;

    /**
     * 信息验证时间-开始
     */
    private Date examVerifyStartTime;

    /**
     * 信息验证时间-结束
     */
    private Date examVerifyEndTime;

    /**
     * 准考证打印时间-开始
     */
    private Date examPrintStartTime;

    /**
     * 准考证打印时间-结束
     */
    private Date examPrintEndTime;

    /**
     * 成绩查询时间-开始
     */
    private Date examScoreStartTime;

    /**
     * 成绩查询时间-结束
     */
    private Date examScoreEndTime;

    /**
     * 总分
     */
    private BigDecimal examTotalScore;

    /**
     * 考试年份
     */
    private String examYear;

    /**
     * 可预约时间-开始
     */
    private Date examOrderStartTime;

    /**
     * 可预约时间-开始
     */
    private Date examOrderEndTime;


}
