package com.lvmoney.frame.ai.isolationforest.ro.item;/**
 * 描述:
 * 包名:com.lvmoney.platform.smart.manager.ro.item
 * 版本信息: 版本1.0
 * 日期:2022/5/12
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/12 11:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IsolationForestParam implements Serializable {
    private static final long serialVersionUID = -5364185614802145503L;
    /**
     * 森林中树的颗数,int, optional (default=100)
     * int, optional (default=100) 指定该森林中生成的随机树数量
     */
    private Integer nEstimators;
    /**
     * 对每棵树，样本个数或比例 nt or float, optional (default=”auto”)
     * 用来训练随机数的样本数量，即子采样的大小
     * 如果设置的是一个int常数，那么就会从总样本X拉取max_samples个样本来生成一棵树iTree
     * 如果设置的是一个float浮点数，那么就会从总样本X拉取max_samples * X.shape[0]个样本,X.shape[0]表示总样本个数
     * 如果设置的是"auto"，则max_samples=min(256, n_samples)，n_samples即总样本的数量
     * 　　如果max_samples值比提供的总样本数量还大的话，所有的样本都会用来构造数，意思就是没有采样了，构造的n_estimators棵iTree使用的样本都是一样的，即所有的样本
     */
    private String maxSamples;
    /**
     * 这是最关键的参数，用户设置样本中异常点的比例,float in (0., 0.5), optional (default=0.1)
     */
    private Float contamination;
    /**
     * 对每棵树，特征个数或比例.int or float, optional (default=1.0)
     */
    private Float maxFeatures;

    /**
     * 如果为True，则各个树可放回地对训练数据进行采样。如果为False，则执行不放回的采样。
     */
    private String bootstrap = "True";
    /**
     * 在运行fit()和predict()函数时并行运行的作业数量。除了在joblib.parallel_backend上下文的情况下，None表示为1。设置为-1则表示使用所有可用的处理器
     * int or None, optional (default=None)
     */
    private Integer nJobs = -1;
    /**
     * random_state : int, RandomState instance or None, optional (default=None)
     * 如果设置为int常数，则该random_state参数值是用于随机数生成器的种子
     * 如果设置为RandomState实例，则该random_state就是一个随机数生成器
     * 如果设置为None，该随机数生成器就是使用在np.random中的RandomState实例
     */
    private Integer randomState;
}
