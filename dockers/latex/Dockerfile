FROM "algorithms-fall2018-public/latex-base:latest"
#
# I know "scheme-full" is tempting, but three hours later you may have wished you picked out the packages...
#
# 1. Try using
#
#  tlmgr search --global --file <missing thing>
#
# to figure out what you need to install.
#
# 2. append extra RUN lines so docker can cache your build steps instead of re-running all of them.
#
# 3. Once you have the packages, combine them into one tlmgr install in sorted order to help docker
#    cache them once and make it easier on your collaborators...
#
# 4. You can nullify multiline RUN lines by replacing inserting a true # at the begining:
#
#    # enabled
#    RUN tlmgr install \
#        <package1> \
#        <package2> \
#        #end
#
#    # disabled
#    RUN true # tlmgr install \
#        <package1> \
#        <package2> \
#        #end
#
#    This format makes it easier to manage a package list in sorted order, which is the
#    recommendation after you figure out all the packages to install through multiple
#    RUN commands.
#

#
# scheme-full (5+GB, 2+hours) - comment out to ignore
#
RUN true || tlmgr install \
    scheme-full

#
# min (remove true # below and comment out scheme-full above to use)
#
RUN true || tlmgr install \
    collection-fontsrecommended
#
# tufte (remove true # below and comment out scheme-full above to use)
#
RUN tlmgr install \
    asymptote \
    booktabs \
    ccicons \
    collection-fontsrecommended \
    fancyvrb \
    fpl \
    genmisc \
    hardwrap \
    l3kernel \
    l3packages \
    lipsum \
    listings \
    mathpazo \
    microtype \
    ms \
    relenc \
    setspace \
    siunitx \
    textcase \
    titlesec \
    tufte-latex \
    units \
    xcolor \
    xkeyval

