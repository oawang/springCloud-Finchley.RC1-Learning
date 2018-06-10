package cn.lframe.product.util;


import cn.lframe.product.vo.ResultVO;

/**
 * 结果
 *
 * @author Lframe
 * @create2018 -05 -02 -14:30
 */
public class ResultUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

}
