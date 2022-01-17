export interface ReservationDTO {
    id: number,
    imgUrl: string,
    name: string,
    approved: boolean,
    startDate: Date,
    price: number,
    durationInHours: number,
    mark: number,
    reportPresent: boolean,
    complaintPresent: boolean,
    reviewPresent: boolean,
    address: string
  }