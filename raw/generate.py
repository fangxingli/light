#!/usr/bin/env python

data = ['Data', 'Text', 'Source', 'HeadImage']
type_map = {
		'int64': 'long',
		'int' : 'int',
		'string' : 'String',
		'string[]' : 'String[]',
		'boolean' : 'boolean',
}
fname = 'sina_raw_data.go'
attributes_array = []
methods_array = []
class_part = ''

data = open(fname).read()
new_data = data.replace('\t', ' ')
data = new_data
if data.find('\t') != -1:
	exit -1

lines = data.split('\n')

def namingClass(name):
	return ''.join([ i.capitalize() for i in name.split('_') ])

def namingAttribute(name):
	return 'm'+namingClass(name)

def buildAttribute(name, name_type, comment):
	if name_type in type_map.keys():
		name_type = type_map[name_type]
	return 'private %s %s;//%s\n' % (name_type, namingAttribute(name), comment)

def buildMethod(name, name_type):
	if name_type in type_map.keys():
		name_type = type_map[name_type]
	return '''
			  public void set%s(%s v){
			  		this.%s = v;
			  }
		
			  public %s get%s(){
					return this.%s;
			  }\n''' % (namingAttribute(name)[1:], 
					  name_type, 
					  namingAttribute(name), 
					  name_type, 
					  namingAttribute(name)[1:],
					  namingAttribute(name)
					  )

for i in lines:
	if i == '':
		continue
	if i[0] == '-':
		class_part = '}\nclass ' + namingClass(i[1:]) + '{\n'
		print '\t' + '\t'.join(attributes_array)
		print '\t' + '\t'.join(methods_array)
		print class_part
		attributes_array = []
		methods_array = []
	else:
		words = i.split(' ')
		#print words
		if words[1] == 'class':
			attributes_array.append( buildAttribute(words[0], words[2] , ''.join(words[2:])) ) 
			methods_array.append( buildMethod(words[0], words[2]) )
		else:
		#	print words[1]
			attributes_array.append( buildAttribute(words[0], type_map[words[1]] , ''.join(words[2:])) )
			methods_array.append( buildMethod(words[0], type_map[words[1]]) )
			

print '\t' + '\t'.join(attributes_array)
print '\t' + '\t'.join(methods_array)
