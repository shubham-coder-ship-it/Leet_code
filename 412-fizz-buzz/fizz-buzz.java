class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> str=new ArrayList<>();
     for(int i=1; i<=n; i++){
        if (i%3==0 && i%5==0){
            str.add("FizzBuzz") ;
        }
        else if (i%3==0){
        str.add("Fizz");
     }
        else if (i%5==0){
         str.add( "Buzz");
     }
     else {
        str.add(i+"");
     }
    
    }
    return str;
    }
}
