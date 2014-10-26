public class BinaryCode {


	public static void main(String args[]){
		BinaryCode bc = new BinaryCode();
		for (String str : bc.decode("123210120")){
			System.out.println(str);
		}
	}

	public String[] decode(String message) {
		String[] answer = new String[2];
		answer[0] = decrypt('0',message);
		answer[1] = decrypt('1',message);
		return answer;
	}

	public String decrypt(char firstchar,String encryptedStr){
		if(encryptedStr==null){
			return "NONE";
		}
		if(encryptedStr.length()==0){
			return "NONE";
		}
		if(encryptedStr.length()==1){
			if(encryptedStr.charAt(0)=='0'){
				return "0";
			}else if(encryptedStr.charAt(0)=='1'){
				return "1";
			}else{
				return "NONE";
			}
		}
		char[] deCrypt =  new char[encryptedStr.length()];
		char[] encryptedChars = encryptedStr.toCharArray();
		deCrypt[0] = firstchar;
		for(int i = 1; i < deCrypt.length;i++){
			if(i==1){
				deCrypt[i] = (char)(((encryptedChars[i-1])-'0')-(deCrypt[i-1]-'0')+'0');
			}else{
				deCrypt[i] = (char)(((encryptedChars[i-1])-'0')-((deCrypt[i-1]-'0') + (deCrypt[i-2]-'0'))+'0');
			}
			if(deCrypt[i]>'1')
				return "NONE";
		}
		//check for last character
		if(encryptedChars[encryptedChars.length-1]-'0' != deCrypt[deCrypt.length-1]-'0' + deCrypt[deCrypt.length-2]-'0'){
			return "NONE";
		}
		return new String(deCrypt);
	}
}
