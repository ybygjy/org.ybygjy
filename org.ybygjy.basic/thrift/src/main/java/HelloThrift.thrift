namespace java org.ybygjy.thrift.hello
service HelloThrift {
	string helloString(1:string para)
	i32 helloInt(1:i32 param)
	bool helloBoolean(1:bool param)
	void helloVoid()
	string helloNull()
}