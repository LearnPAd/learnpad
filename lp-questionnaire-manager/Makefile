CC=gcc
SH=bash
SRC_PATH=src
SRC=$(SRC_PATH)/hello.c
OBJ=$(SRC_PATH)/hello.o
BIN_PATH=bin
BIN=$(BIN_PATH)/hello
CFLAGS=-Wall -Werror
LDFLAGS=

all: $(BIN)

$(BIN): $(OBJ)
	mkdir -p $(BIN_PATH)
	$(CC) -o $@ $^ $(LDFLAGS)
$(OBJ): $(SRC)
	$(CC) -c -o $@ $^ $(CFLAGS)

clean:
	rm --force $(BIN) $(OBJ)
