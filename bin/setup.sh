#!/bin/bash

get_script_dir () {
    pushd $(dirname "${BASH_SOURCE[0]}") >/dev/null
    pwd
    popd >/dev/null
}

THIS_DIR=$(get_script_dir)
BASE_DIR=$(dirname "$THIS_DIR")

if ! which docker > /dev/null
then
    echo "you must install/run docker..."
    echo 'mac:'
    echo '    open "https://download.docker.com/mac/stable/Docker.dmg"'
    echo 'win:'
    echo '    start "https://download.docker.com/win/stable/Docker%20for%20Windows%20Installer.exe"'
    echo 'ubuntu:'
    echo "    bash \"$BASE_DIR/bin/setup-docker-ubuntu.sh\""
    exit 1
fi

(cd "$BASE_DIR/dockers" && docker build -t texlive texlive)

cat >"$BASE_DIR/context" <<EOF  
#!/bin/bash
if ! echo \$PATH | egrep "(^|:)$BASE_DIR/bin(:|\$)" >/dev/null
then
   echo "prefix path with $BASE_DIR/bin..."
   export PATH="$BASE_DIR/bin:\$PATH"
else
   echo "$BASE_DIR/bin is already in your path"
fi
EOF

echo "Type ". ./context" in the project base directory ($BASE_DIR) to set your PATH"



