package com.lvmoney.frame.ai.seetaface.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 人脸识别原图
 * </p>
 *
 * @author lvmoney
 * @since 2022-02-11
 */
public class ComparisonResource implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String idCard;

    private String idCardPrivacy;

    private String fileId;

    private String fileName;

    private String fileType;

    /**
     * 创建人id
     */
    private Long createId;

    /**
     * 创建人id
     */
    private Long clientId;

    /**
     * 更新人id
     */
    private Long updateId;

    /**
     * 删除标识，0：删除，1：未删除
     */
    private Boolean valid;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    private String createName;

    private String updateName;

    private String extra;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardPrivacy() {
        return idCardPrivacy;
    }

    public void setIdCardPrivacy(String idCardPrivacy) {
        this.idCardPrivacy = idCardPrivacy;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "ComparisonResource{" +
        "id=" + id +
        ", idCard=" + idCard +
        ", idCardPrivacy=" + idCardPrivacy +
        ", fileId=" + fileId +
        ", fileName=" + fileName +
        ", fileType=" + fileType +
        ", createId=" + createId +
        ", clientId=" + clientId +
        ", updateId=" + updateId +
        ", valid=" + valid +
        ", tenantId=" + tenantId +
        ", updateDate=" + updateDate +
        ", createDate=" + createDate +
        ", createName=" + createName +
        ", updateName=" + updateName +
        ", extra=" + extra +
        "}";
    }
}
