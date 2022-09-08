#include<stdio.h>
int my_strlen(const char *s){
    int cnt=0;
    for(int i=0;*s!='\0';i++){
        *s++;
        cnt++;
    }
    return cnt;
}
void reverse(char *s,int n){
    for(int i=0;i<n/2;i++){
        char temp;
        temp=s[i];
        s[i]=s[n-i-1];
        s[n-i-1]=temp;
    }
}
int main(){
    char s[10000];
    scanf("%s",s);
    int n=my_strlen(s);
    
    reverse(s,n);
    for(int i=0;i<n;i++){
        printf("%c",s[i]);
    }
    return 0;
}