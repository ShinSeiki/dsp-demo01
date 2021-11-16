import { Observable, Subject } from "rxjs";
import { BaseModel } from "../model/base-model.model";
import { EntityFilterService } from "../service/baseentity.service";

export abstract class BaseNavRecord<T extends BaseModel<T, ID>, ID> {

  rows: T[] = [];

  ngOnInit(): void {
    this.doLoad();
  }

  abstract doLoad(): void;

}
