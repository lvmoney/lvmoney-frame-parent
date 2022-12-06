import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns
from pyod.models.auto_encoder_torch import AutoEncoder
from pyod.models.cblof import CBLOF
from pyod.models.copod import COPOD
from pyod.models.ecod import ECOD
from pyod.models.hbos import HBOS
from pyod.models.iforest import IForest
from pyod.models.knn import KNN
from pyod.models.mcd import MCD
from pyod.models.ocsvm import OCSVM
from pyod.models.pca import PCA
from pyod.models.rod import ROD
from sklearn.preprocessing import MinMaxScaler
from sklearn import decomposition
import Const
import PyodConst

sns.set_theme()
sns.set_style("darkgrid", {"font.sans-serif": ['simhei', 'Droid Sans Fallback']})

plt.rcParams['font.sans-serif'] = ['SimHei']
plt.rcParams['axes.unicode_minus'] = False


def pyodPredict(data, field, resultFiled,
                errorFraction):
    inputData = data.copy()
    # 数据缩放：将数据标准化处理将其缩放到0到1之间。
    scaler = MinMaxScaler(feature_range=(0, 1))
    data = data[field]
    data[field] = scaler.fit_transform(data)

    if (len(field) <= 1):
        copyField = field[0]
        data[copyField + PyodConst.PYOD_DATA_FILED_COPY_SUFFIX] = data[field]
    classifiers = {
        # 耗时：
        # 算法 HBOS 耗时 0:00:03.122496
        # 算法名称: IF
        # 算法 IF 耗时 0:00:01.182865
        # 算法名称: AKNN
        # 算法 AKNN 耗时 0:00:02.273891
        # 算法名称: MCD
        # 算法 MCD 耗时 0:00:02.957091
        # 算法名称: PCA
        # 算法 PCA 耗时 0:00:00.007979
        # 算法名称: COPOD
        # 算法 COPOD 耗时 0:00:00.036932
        # 算法名称: ECOD
        # 算法 ECOD 耗时 0:00:00.035901
        # 算法名称: ROD
        # 算法 ROD 耗时 0:00:03.716033
        # 算法名称: KNN
        # 算法 KNN 耗时 0:00:01.854070
        # 算法名称: CBLOF
        # 算法 CBLOF 耗时 0:00:01.661527
        # 耗时排序：AutoEncoder，OCSVM,ROD,HBOS,MCD,AKNN，KNN，CBLOF,IF,COPOD,ECOD,PCA
        # 耗时非常长长：OCSVM,AutoEncoder
        # 耗时长：ROD,HBOS
        # 耗时较长：MCD,AKNN
        # 耗时一般：KNN，CBLOF,IF
        # 耗时最短：COPOD,ECOD,PCA
        # 为了效率选择4种：IF，COPOD,ECOD,PCA
        #
        # PyodConst.PYOD_FUNCTION_CLASSIFY_AUTO_ENCODER_TORCH: AutoEncoder(
        #     contamination=errorFraction),
        #
        # PyodConst.PYOD_FUNCTION_CLASSIFY_OCSVM: OCSVM(contamination=errorFraction),
        # PyodConst.PYOD_FUNCTION_CLASSIFY_ROD: ROD(
        #     contamination=errorFraction),
        # PyodConst.PYOD_FUNCTION_CLASSIFY_HBOS: HBOS(
        #     contamination=errorFraction),
        # PyodConst.PYOD_FUNCTION_CLASSIFY_MCD: MCD(
        #     contamination=errorFraction, random_state=0),
        # PyodConst.PYOD_FUNCTION_CLASSIFY_KNN_AVERAGE: KNN(method='mean',
        #                                                   contamination=errorFraction),
        # PyodConst.PYOD_FUNCTION_CLASSIFY_KNN: KNN(
        #     contamination=errorFraction),
        # PyodConst.PYOD_FUNCTION_CLASSIFY_CBLOF:
        #     CBLOF(contamination=errorFraction,
        #           check_estimator=False, random_state=0),
        PyodConst.PYOD_FUNCTION_CLASSIFY_ISOLATION_FOREST: IForest(contamination=errorFraction,
                                                                   random_state=0),
        PyodConst.PYOD_FUNCTION_CLASSIFY_COPOD: COPOD(
            contamination=errorFraction),

        PyodConst.PYOD_FUNCTION_CLASSIFY_ECOD: ECOD(
            contamination=errorFraction),
        PyodConst.PYOD_FUNCTION_CLASSIFY_PCA: PCA(
            contamination=errorFraction, random_state=0),

    }

    pca = decomposition.PCA(n_components=PyodConst.PYOD_PCA_DATA_DIMENSION_DEFAULT)
    X = pca.fit_transform(data)

    resultDf = pd.DataFrame()
    for i, (clf_name, clf) in enumerate(classifiers.items()):
        # 训练数据
        clf.fit(X)
        # 预测异常值分数
        # 预测异常值和正常值的数据
        y_pred = clf.predict(X)
        result = inputData
        result[PyodConst.PYOD_PCA_DATA_DIMENSION_RESULT] = y_pred
        abnormal = result[result[resultFiled] == PyodConst.PYOD_PRED_RESULT_ERROR]
        append = resultDf.append(abnormal)
        '''
        将12种异常结果合并，将所有结果作为异常
        取并集
        '''
        resultDf = append.drop_duplicates(subset=[Const.SMART_DATA_UNIQUE_ID], keep='first')
    # 程序代码段运行
    all = inputData.drop(resultFiled, axis=1)
    item = resultDf.drop(resultFiled, axis=1)
    '''
    所有数据排除异常数据即为正常数据
    取差集
    '''
    normal = pd.concat([all, item, item]).drop_duplicates(keep=False)

    iField = field.copy()
    iField.append(Const.SMART_DATA_UNIQUE_ID)
    iField.append(Const.SMART_DATA_FILED_TIME)
    # 获得需要insert字段的数据
    normal = normal[iField]

    return normal
