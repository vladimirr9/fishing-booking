export interface ReservationDTO {
    id: number,
    imgUrl: string,
    name: string,
    approved: boolean,
    startDate: Date,
    price: number,
    durationInHours: number,
    mark: number,
    address: string
  }