package com.lvmoney.frame.ai.lstm.ro.item;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.lstm.ro.item
 * 版本信息: 版本1.0
 * 日期:2022/6/16
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/6/16 14:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LstmParam implements Serializable {

    private static final long serialVersionUID = 2707649489101016358L;
    /**
     * 神经网络训练的轮次,控制执行次数
     */
    private String[] epochs;
    /**
     * 每批过神经网络的大小
     */
    private String[] batchSize;
    /**
     * mean_squared_error或mse
     * mean_absolute_error或mae
     * mean_absolute_percentage_error或mape
     * mean_squared_logarithmic_error或msle
     * squared_hinge
     * hinge
     * binary_crossentropy（亦称作对数损失，logloss）
     * categorical_crossentropy：亦称作多类的对数损失，注意使用该目标函数时，需要将标签转化为形如(nb_samples, nb_classes)的二值序列
     * sparse_categorical_crossentrop：如上，但接受稀疏标签。注意，使用该函数时仍然需要你的标签与输出值的维度相同，你可能需要在标签数据上增加一个维度：np.expand_dims(y,-1)
     * kullback_leibler_divergence:从预测值概率分布Q到真值概率分布P的信息增益,用以度量两个分布的差异.
     * cosine_proximity：即预测值与真实标签的余弦距离平均值的相反数
     * <p>
     * mean_squared_error,adam
     */
    private String[] loss;
    /**
     * SGD，Adagrad，Adadelta，Adam，Adamax，Nadam
     */
    private String[] optimizer;

    /**
     * Dropout是一种训练时可以采用的正则化方法，通过在正向传递和权值更新的过程中对LSTM神经元的输入和递归连接进行概率性失活，该方法能有效避免过拟合并该善模型性能
     */
    private String[] dropout;
    /**
     * 可视化图标题
     */
    private String title;
    /**
     * 训练数据百分比
     */
    private String percent;
    /**
     * listUnits
     */
    private String[] listUnits;
    /**
     * denseUnits
     */
    private String[] denseUnits;
    /**
     *
     */
    private String nJobs;
    /**
     * verbose
     */
    private String verbose;


}
