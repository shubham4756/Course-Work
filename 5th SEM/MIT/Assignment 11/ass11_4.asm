dosseg
.model small

.data
n db ?
list db 80 dup(0)
key db ?
numMsg db "How many digits? $"
valMsg db "Enter the value: $"
getKey db "Enter the key: $"
displayMsg db "The count of the given key is: $"

cnt db 0
;this macro will print a string terminated by $
printstring macro msg
mov ah, 09h ; write a $ terminated string to standard output    
mov dx, offset msg 
int 21h
endm

;print a character
printchar macro ch
mov ah, 02h
mov dl, ch
int 21h
endm

scan2 macro num
mov ah, 01h ;read a char
int 21h
sub al, 30h 
mov bh, 0ah
mul bh
mov bh, al ;store first one in bh
mov ah, 01h ; read next character
int 21h
sub al, 30h ;ascii to number
add bh, al
mov num, bh
printchar 0dh
printchar 0ah
 endm

; ; scan an array and store in list of size n
scanarray2 macro list, n
mov si, offset list
mov ch, 00
mov cl, n
up: 
printstring valMsg
scan2 [si]
inc si 
loop up
endm


; ;print a number
print2 macro num
printchar 0dh
printchar 0ah
mov al, num
add al, 0h
daa
mov bl, al
and al, 0F0h
shr al, 04h
add al,30h
printchar al
and bl, 0Fh
add bl,30h 
printchar bl
endm

; ;print an array of 2dig num
printarray2 macro list, n
mov si, offset list
mov ch, 00
mov cl, n
up1:
print2 [si]
inc si
printchar 0dh
printchar 0ah 
loop up1
endm

findOccurence macro list, n, key, cnt
    mov si, offset list
    mov ch, 00
    mov cl, n
    upa:
    mov al, [si]
    cmp key, al 
    jne skipa
    inc cnt 
    skipa: inc si
    loop upa 
endm

.code
mov ax, @data
mov ds, ax

printstring numMsg
scan2 n
scanarray2 list, n

printstring getKey 
scan2 key

findOccurence list, n, key, cnt
printstring displayMsg
print2 cnt

mov ah, 4ch
int 21h
end