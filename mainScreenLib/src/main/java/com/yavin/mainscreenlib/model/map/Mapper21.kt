package com.yavin.mainscreenlib.model.map

interface Mapper21<SRC1, SRC2, DST> {
    fun map(src1: SRC1, src2: SRC2): DST
}