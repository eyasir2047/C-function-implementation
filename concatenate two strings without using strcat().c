//concatenate two strings
#include<stdio.h>
int main(){
    char s1[1000],s2[1000];
    scanf("%s",s1);
    scanf("%s",s2);
    int len=0;
    int i=0;
    while(s1[i]!='\0'){
        i++;
    }
    len=i;
    for(int j=0;s2[j]!='\0';j++){
        s1[len++]=s2[j];
    }
    s1[len]='\0';
    puts(s1);
    
    return 0;
}