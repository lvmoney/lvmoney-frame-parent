import paddlex as pdx
model = pdx.load_model('D:/work/paddle/P0006-T0006_export_model')
result = model.predict('D:/data/paddle/makeup/mascara/54.jpeg', topk=1)
print("Predict Result:", result)
