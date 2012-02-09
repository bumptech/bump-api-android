all:
	rm -rf bin/*
	ant debug

install:
	make
	adb devices | fgrep -v attached | tr "\t" "," | sed -e 's/,.*//' | xargs -I {} adb -s {} install -r bin/BumpTest-debug.apk

