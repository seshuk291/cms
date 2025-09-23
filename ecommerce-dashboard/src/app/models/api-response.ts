export class ApiResponse<T> {
   public message?:string;
   public success?: boolean;
   public data?:T;
   public timeStamp?:string;
}