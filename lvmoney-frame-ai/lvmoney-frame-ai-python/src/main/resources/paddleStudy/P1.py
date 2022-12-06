class Person:
    def __init__(self,name):
        self.name = name
        print ('调用父类构造函数')

    def eat(self):
        print('调用父类方法')

class Student(Person):  # 定义子类
    def __init__(self):
        print ('调用子类构造方法')

    def study(self):
        print('调用子类方法')

s = Student()          # 实例化子类
s.study()              # 调用子类的方法
s.eat()                # 调用父类方法