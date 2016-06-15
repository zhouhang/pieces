package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

public class JztTask implements Serializable {
    /**
     * ZYC.JZT_TASK.TASKID (主键(uuid))
     * @ibatorgenerated 2015-01-04 15:05:08
     */
    private String taskid;

    /**
     * ZYC.JZT_TASK.TASKTYPE (任务类型，1表示交易)
     * @ibatorgenerated 2015-01-04 15:05:08
     */
    private Short tasktype;

    /**
     * ZYC.JZT_TASK.TASKNAME (任务名称)
     * @ibatorgenerated 2015-01-04 15:05:08
     */
    private String taskname;

    /**
     * ZYC.JZT_TASK.JOBCLASS (任务class)
     * @ibatorgenerated 2015-01-04 15:05:08
     */
    private String jobclass;

    /**
     * ZYC.JZT_TASK.CRONEXPRESSION (时间规则表达式)
     * @ibatorgenerated 2015-01-04 15:05:08
     */
    private String cronexpression;

    /**
     * ZYC.JZT_TASK.ISENABLE (1表示启动，2表示不启动)
     * @ibatorgenerated 2015-01-04 15:05:08
     */
    private Short isenable;

    /**
     * ZYC.JZT_TASK.TASKREMARK (任务描述)
     * @ibatorgenerated 2015-01-04 15:05:08
     */
    private String taskremark;

    /**
     * ZYC.JZT_TASK.CREATETIME (新建日期)
     * @ibatorgenerated 2015-01-04 15:05:08
     */
    private Date createtime;

    /**
     * ZYC.JZT_TASK.UPDATETIME (修改日期)
     * @ibatorgenerated 2015-01-04 15:05:08
     */
    private Date updatetime;

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public Short getTasktype() {
        return tasktype;
    }

    public void setTasktype(Short tasktype) {
        this.tasktype = tasktype;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getJobclass() {
        return jobclass;
    }

    public void setJobclass(String jobclass) {
        this.jobclass = jobclass;
    }

    public String getCronexpression() {
        return cronexpression;
    }

    public void setCronexpression(String cronexpression) {
        this.cronexpression = cronexpression;
    }

    public Short getIsenable() {
        return isenable;
    }

    public void setIsenable(Short isenable) {
        this.isenable = isenable;
    }

    public String getTaskremark() {
        return taskremark;
    }

    public void setTaskremark(String taskremark) {
        this.taskremark = taskremark;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}