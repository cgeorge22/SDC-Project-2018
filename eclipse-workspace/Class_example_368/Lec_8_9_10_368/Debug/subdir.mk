################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../Lecture_10.cpp \
../Lecture_8.cpp \
../Lecture_9.cpp \
../POINT.cpp 

OBJS += \
./Lecture_10.o \
./Lecture_8.o \
./Lecture_9.o \
./POINT.o 

CPP_DEPS += \
./Lecture_10.d \
./Lecture_8.d \
./Lecture_9.d \
./POINT.d 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


