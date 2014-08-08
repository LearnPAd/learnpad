CC=gcc
SH=bash
SRC=src/hello.c
OBJ=src/hello.o
BIN=bin/hello
CFLAGS=-Wall -Werror
LDFLAGS=

all: $(BIN)

$(BIN): $(OBJ)
	$(CC) -o $@ $^ $(LDFLAGS)
$(OBJ): $(SRC)
	$(CC) -c -o $@ $^ $(CFLAGS)

clean:
	rm --force $(BIN) $(OBJ)
