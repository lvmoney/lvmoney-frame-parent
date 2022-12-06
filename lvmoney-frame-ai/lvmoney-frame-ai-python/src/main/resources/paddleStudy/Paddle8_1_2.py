import paddlehub as hub
lac = hub.Module(name="lac")
test_text = ["1996年，曾经是微软员工的加布·纽维尔和麦克·哈灵顿一同创建了Valve软件公司。他们在1996年下半年从id software取得了雷神之锤引擎的使用许可，用来开发半条命系列。"]
res = lac.lexical_analysis(texts = test_text)

print("中文词法分析结果：", res)

import paddlehub as hub
senta = hub.Module(name="senta_bilstm")
test_text = ["味道不错，确实不算太辣，适合不能吃辣的人。就在长江边上，抬头就能看到长江的风景。鸭肠、黄鳝都比较新鲜。"]
res = senta.sentiment_classify(texts = test_text)

print("中文词法分析结果：", res)