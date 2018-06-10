package cn.lframe.order.util;

import cn.lframe.order.vo.ResultVO;

/**
 * @author Lframe
 * @create2018 -05 -03 -10:48
 */
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO  = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMeg("成功");
        resultVO.setData(object);
        return resultVO;
    }
}
