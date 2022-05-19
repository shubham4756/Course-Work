dosseg 
model small
.8086

.data
s1 db "UVWXYZ$"
s2 db "ABCDEF$"

.code
mov ax, @data
mov ds, ax

mov ah,15h  
mov bh,al   
mov di,offset s1
mov bl,[di] 
mov ax,[0012h]
mov si, offset s2
mov dl,[si+2]
in ax , 50H

mov ax, 4C00H
int 21h
end