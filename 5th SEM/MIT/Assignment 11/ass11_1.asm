.model small
.data

 sum dw ?
 carry db ?
 List dw 256,256,256,256,256

.code      

  mov ax,@data
  mov ds,ax
    mov ax, 0000H
    mov cx, 05H ;counter
    mov bl, 00H ;to count carry
    mov si, offset List ;init si to start of List

    up:
    add ax, [si]
    JNC skip
    INC bl ;
    skip: 
    inc si
    inc si
    loop up 

    mov sum, ax 
    mov carry, bl

    mov ah, 4ch
    int 21h
  end
