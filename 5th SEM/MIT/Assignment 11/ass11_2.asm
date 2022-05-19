.model small
.data

 max dw ?
 List dw 256,257,258,259,260

.code      

  mov ax,@data
  mov ds,ax
    mov ax, 0000H
    mov cx, 05H ;counter
    mov bl, 00H ;to count carry
    mov si, offset List ;init si to start of List

    up:
    cmp ax, [si]
    JNC skip
    mov ax, [si]
    skip:
    inc si
    inc si
    loop up 

    mov max, ax
    mov ah, 4ch
    int 21h
  end
