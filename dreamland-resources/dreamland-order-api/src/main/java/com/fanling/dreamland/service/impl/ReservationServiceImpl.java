package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.entity.Reservation;
import com.fanling.dreamland.mapper.ReservationMapper;
import com.fanling.dreamland.service.IReservationService;
import com.fanling.common.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务订购/预约表的业务实现层
 */
@Service
public class ReservationServiceImpl extends ServiceImpl<ReservationMapper,Reservation> implements IReservationService {

}
