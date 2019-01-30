import numpy as np
import cv2
import serial
import time 

#serial startup
ser=serial.Serial()
ser.baudrate=9600
ser.port='com3'
ser.open()

cap=cv2.VideoCapture(0)

cm_to_pixel=11.3/640.0
Rad=(105.0/180.0)*np.pi
R180X=[[1,0,0],[0,np.cos(np.pi),-np.sin(np.pi)],[0,np.sin(np.pi),np.cos(np.pi)]]
RZ=[[np.cos(Rad),-np.sin(Rad),0],[np.sin(Rad),np.cos(Rad),0],[0,0,1]]
R0_C=np.dot(RZ,R180X)
d0_C=[[-5.5],[+0.2],[0]]
H0_C=np.concatenate((R0_C,d0_C),1)
H0_C=np.concatenate((H0_C,[[0,0,0,1]]),0)
while(1):
    _,frame=cap.read()
    red=frame[:,:,2]
    green=frame[:,:,1]
    blue=frame[:,:,0]

    gray_image1=cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)

    cv2.imshow('background',gray_image1)
    
    red_only=np.int16(red)-np.int16(blue)-np.int16(green)

    red_only[red_only<0]=0
    red_only[red_only>255]=255

    colunm_sums=np.matrix(np.sum(red_only,0))
    colunm_numbers=np.matrix(np.arange(640))
    colunm_mult=np.multiply(colunm_sums,colunm_numbers)
    total=np.sum(colunm_mult)
    total_total=np.sum(np.sum(red_only))
    colunm_location=total/total_total
    
    #cv2.imshow('RGB',frame)
    #cv2.imshow('red',red)
    #cv2.imshow('green',green)
    #cv2.imshow('blue',blue)
    k=cv2.waitKey(5)
    if k==27:
        break

while(1):
    _,frame=cap.read()
    gray_image2=cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)

    cv2.imshow('foreground',gray_image2)
    Difference=np.absolute(np.matrix(np.int16(gray_image1))-np.matrix(np.int16(gray_image2)))
    Difference[Difference>255]=255
    Difference=np.uint8(Difference)
    cv2.imshow('difference',Difference)

    BW=Difference
    BW[BW<=100]=0
    BW[BW>100]=1

    colunm_sums=np.matrix(np.sum(BW,0))
    #print(colunm_sums)
    colunm_numbers=np.matrix(np.arange(640))
    colunm_mult=np.multiply(colunm_sums,colunm_numbers)
    total=np.sum(colunm_mult)
    total_total=np.sum(np.sum(BW))
    colunm_location=total/total_total
    X_location=colunm_location*cm_to_pixel

    row_sums=np.transpose(np.matrix(np.sum(BW,1)))
    row_numbers=np.matrix(np.arange(480))
    row_mult=np.multiply(row_sums,row_numbers)
    row_total=np.sum(row_mult)
    row_total_total=np.sum(np.sum(BW))
    row_location=row_total/row_total_total
    Y_location=row_location*cm_to_pixel

    PC=[[X_location],[Y_location],[0],[1]]

    P0=np.dot(H0_C,PC)

    X0=P0[0]
    Y0=P0[1]

    print(X0,Y0)
    
    k=cv2.waitKey(5)
    if k==27:
        break
cv2.destroyAllWindows()
X0=abs(X0+1)
X0= int((X0*10))
i=bytearray([X0])
ser.write(i)
time.sleep(.500)
Y0=abs(Y0+1)
Y0=int(Y0*10)
i=bytearray([Y0])
ser.write(i)
print(X0,Y0)

print(frame)
ser.close()
