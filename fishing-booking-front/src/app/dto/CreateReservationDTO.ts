export interface CreateReservationDTO{
    price: number,
    from: Date,
    to: Date,
    clientUsername: string,
    entityId: number,
    type: string
}