# Cloned from: https://github.com/blang/latex-docker/blob/master/Dockerfile.basic

FROM ubuntu:xenial
MAINTAINER Warren MacEvoy <wmacevoy@gmail.com>
ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update -q \
    && apt-get install -qy build-essential wget libfontconfig1 \
    && rm -rf /var/lib/apt/lists/*

# Install TexLive with scheme-basic
RUN wget http://mirror.ctan.org/systems/texlive/tlnet/install-tl-unx.tar.gz; \
	mkdir /install-tl-unx; \
	tar -xvf install-tl-unx.tar.gz -C /install-tl-unx --strip-components=1; \
    echo "selected_scheme scheme-basic" >> /install-tl-unx/texlive.profile; \
	/install-tl-unx/install-tl -profile /install-tl-unx/texlive.profile; \
    rm -r /install-tl-unx; \
	rm install-tl-unx.tar.gz

ENV PATH="/usr/local/texlive/2018/bin/x86_64-linux:${PATH}"

ENV HOME /data
WORKDIR /data

# Install latex packages
RUN tlmgr install latexmk
RUN tlmgr install tufte-latex
RUN tlmgr install asymptote
RUN tlmgr install xkeyval
RUN tlmgr install titlesec
RUN tlmgr install ms
RUN tlmgr install textcase
RUN tlmgr install setspace
RUN tlmgr install relenc
RUN tlmgr install ec
RUN tlmgr install xcolor
RUN tlmgr install booktabs
RUN tlmgr install listings
RUN tlmgr install siunitx
RUN tlmgr install l3kernel
RUN tlmgr install l3packages
RUN tlmgr install genmisc
RUN tlmgr install microtype
RUN tlmgr install ccicons
RUN tlmgr install lipsum
RUN tlmgr install units
RUN tlmgr install fancyvrb
RUN tlmgr install palatino mathpazo fpl
VOLUME ["/data"]
